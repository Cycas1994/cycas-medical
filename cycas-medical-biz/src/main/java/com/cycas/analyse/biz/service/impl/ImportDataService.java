package com.cycas.analyse.biz.service.impl;

import cn.hutool.core.text.StrPool;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSON;
import com.cycas.analyse.biz.entity.ImportDetailRecord;
import com.cycas.analyse.biz.entity.ImportRecord;
import com.cycas.analyse.biz.entity.InHospital;
import com.cycas.analyse.biz.entity.InHospitalDetail;
import com.cycas.analyse.biz.manager.ImportRecordManager;
import com.cycas.analyse.biz.model.bo.InHospitalDetailImportExcelBO;
import com.cycas.analyse.biz.model.bo.InHospitalImportExcelBO;
import com.cycas.analyse.biz.service.ImportDetailRecordService;
import com.cycas.analyse.biz.service.InHospitalDetailService;
import com.cycas.analyse.biz.service.InHospitalService;
import com.cycas.common.biz.exception.BizException;
import com.cycas.common.biz.util.AssertUtil;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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
    private ImportDetailRecordService importDetailRecordService;
    @Autowired
    private InHospitalService inHospitalService;
    @Autowired
    private InHospitalDetailService inHospitalDetailService;

    @Transactional(rollbackFor = Exception.class)
    public String inHospitalImport(MultipartFile file) {
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
            importRecord.markSuccess(totalNum.get(), 0L);
        } catch (IOException e) {
            importRecord.markFailure(e.getMessage());
            throw new BizException("导入异常，请稍后再试");
        } finally {
            // 更新导入记录
            importRecordManager.updateStatus(importRecord);
        }
        return importRecord.getUuid();
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
        AtomicLong totalNum = new AtomicLong();
        AtomicLong failureNum = new AtomicLong();
        PageReadListener<InHospitalDetailImportExcelBO> listener = new PageReadListener<>(dataList -> {
            // 解析
            this.analysisData(dataList, importRecord.getUuid(), totalNum, failureNum);
            // 失败入库
            this.saveFailData(importRecord.getUuid(), dataList);
        });
        try {
            EasyExcelFactory.read(file.getInputStream(), InHospitalDetailImportExcelBO.class, listener)
                    .charset(Charset.forName("GBK"))
                    .sheet()
                    .doRead();
            importRecord.markSuccess(totalNum.get(), failureNum.get());
        } catch (IOException e) {
            importRecord.markFailure(e.getMessage());
            throw new BizException("导入异常，请稍后再试");
        } finally {
            // 更新导入记录
            importRecordManager.updateStatus(importRecord);
        }
    }

    private void analysisData(List<InHospitalDetailImportExcelBO> dataList, String importUuid,
                              AtomicLong totalNum, AtomicLong failureNum) {
        for (InHospitalDetailImportExcelBO bo : dataList) {
            long currNum = totalNum.incrementAndGet() + 1;
            try {
                InHospitalDetail inHospitalDetail = InHospitalDetail.from(bo);
                inHospitalDetail.setImportUuid(importUuid);
                // 保存明细数据
                inHospitalDetailService.save(inHospitalDetail);
            } catch (Exception e) {
                failureNum.incrementAndGet();
                bo.markFailure(currNum, e.getMessage());
                log.error(String.format("当前行号:%s,出现异常", currNum), e);
            }
        }
    }


    private void saveFailData(String importUuid, List<InHospitalDetailImportExcelBO> dataList) {
        List<ImportDetailRecord> list = dataList.stream()
                .filter(InHospitalDetailImportExcelBO::isFailFlag)
                .map(bo -> new ImportDetailRecord()
                        .setImportUuid(importUuid)
                        .setRowNum(bo.getRowNum())
                        .setRowJson(JSON.toJSONString(bo))
                        .setFailureReason(bo.getFailureReason()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        importDetailRecordService.saveBatch(list);
    }
}
