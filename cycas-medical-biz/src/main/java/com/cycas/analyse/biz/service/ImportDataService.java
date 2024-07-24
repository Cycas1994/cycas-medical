package com.cycas.analyse.biz.service;

import cn.hutool.core.text.StrPool;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.read.listener.PageReadListener;
import com.cycas.analyse.biz.entity.ImportRecord;
import com.cycas.analyse.biz.entity.InHospital;
import com.cycas.analyse.biz.entity.InHospitalDetail;
import com.cycas.analyse.biz.manager.ImportRecordManager;
import com.cycas.analyse.biz.model.bo.InHospitalDetailImportExcelBO;
import com.cycas.analyse.biz.model.bo.InHospitalImportExcelBO;
import com.cycas.common.biz.util.AssertUtil;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static com.cycas.analyse.biz.constant.ImportDataConstant.IMPORT_RECORD_TYPE_DETAIL;
import static com.cycas.analyse.biz.constant.ImportDataConstant.IMPORT_RECORD_TYPE_MAIN;

/**
 * @author xin.na
 * @since 2024/7/19 13:55
 */
@Slf4j
@Service
public class ImportDataService {

    private static final List<String> ALLOW_FILE_SUFFIX = ImmutableList.of("csv");
    private static final long MAX_FILE_SIZE = 104857600;

    @Autowired
    private ImportRecordManager importRecordManager;
    @Autowired
    private InHospitalService inHospitalService;
    @Autowired
    private InHospitalDetailService inHospitalDetailService;

    @Transactional(rollbackFor = Exception.class)
    public void inHospitalImport(MultipartFile file) {
        String filename = file.getOriginalFilename();
        String fileType = StringUtils.substringAfterLast(filename, StrPool.DOT).toLowerCase();
        AssertUtil.isTrue(StringUtils.isNotBlank(filename), "上传文件名称不能为空");
        AssertUtil.isTrue(ALLOW_FILE_SUFFIX.contains(fileType), "上传文件类型错误，只允许上传csv文件");
        AssertUtil.isTrue(file.getSize() <= MAX_FILE_SIZE, "文件大小超过%dM", (MAX_FILE_SIZE / 1024 / 1024));
        // 保存导入记录
        ImportRecord importRecord = importRecordManager.saveOnce(filename, IMPORT_RECORD_TYPE_MAIN);
        try {
            AtomicLong totalNum = new AtomicLong();
            EasyExcelFactory.read(file.getInputStream(), InHospitalImportExcelBO.class, new PageReadListener<InHospitalImportExcelBO>(dataList -> {
                List<InHospital> list = dataList.stream().map(data -> {
                    InHospital inHospital = InHospital.from(data);
                    inHospital.setImportUuid(importRecord.getUuid());
                    return inHospital;
                }).collect(Collectors.toList());
                // 保存住院主表数据
                inHospitalService.saveBatch(list);
                totalNum.addAndGet(list.size());
            })).charset(Charset.forName("GBK")).sheet().doRead();
            importRecord.markSuccess(totalNum.get());
        } catch (IOException e) {
            importRecord.markFailure(e.getMessage());
            throw new RuntimeException(e);
        } catch (Throwable e) {
            importRecord.markFailure(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            // 更新导入记录
            importRecordManager.updateStatus(importRecord);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void inHospitalDetailImport(MultipartFile file) {
        String filename = file.getOriginalFilename();
        String fileType = StringUtils.substringAfterLast(filename, StrPool.DOT).toLowerCase();
        AssertUtil.isTrue(StringUtils.isNotBlank(filename), "上传文件名称不能为空");
//        AssertUtil.isTrue(ALLOW_FILE_SUFFIX.contains(fileType), "上传文件类型错误，只允许上传csv文件");
        AssertUtil.isTrue(file.getSize() <= MAX_FILE_SIZE, "文件大小超过%dM", (MAX_FILE_SIZE / 1024 / 1024));
        // 保存导入记录
        ImportRecord importRecord = importRecordManager.saveOnce(filename, IMPORT_RECORD_TYPE_DETAIL);
        try {
            AtomicLong totalNum = new AtomicLong();
            EasyExcelFactory.read(file.getInputStream(), InHospitalDetailImportExcelBO.class, new PageReadListener<InHospitalDetailImportExcelBO>(dataList -> {
                log.info("开始处理{}到{}行数据", totalNum.get(), totalNum.get() + dataList.size());
                List<InHospitalDetail> list = dataList.stream().map(data -> {
                    InHospitalDetail inHospitalDetail = InHospitalDetail.from(data);
                    inHospitalDetail.setImportUuid(importRecord.getUuid());
                    return inHospitalDetail;
                }).collect(Collectors.toList());
                // 保存住院主表数据
                inHospitalDetailService.saveBatch(list);
                totalNum.addAndGet(list.size());
            })).charset(Charset.forName("GBK")).sheet().doRead();
            importRecord.markSuccess(totalNum.get());
        } catch (IOException e) {
            importRecord.markFailure(e.getMessage());
            throw new RuntimeException(e);
        } catch (Throwable e) {
            importRecord.markFailure(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            // 更新导入记录
            importRecordManager.updateStatus(importRecord);
        }
    }
}
