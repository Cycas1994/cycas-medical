package com.cycas.analyse.biz.manager;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cycas.analyse.biz.entity.ImportRecord;
import com.cycas.analyse.biz.mapper.ImportRecordMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author xin.na
 * @since 2024/7/19 14:01
 */
@Service
public class ImportRecordManager {

    @Resource
    private ImportRecordMapper mapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ImportRecord saveOnce(String name, Integer type) {
        ImportRecord record = new ImportRecord();
        record.setUuid(IdWorker.get32UUID());
        record.setFileName(name);
        record.setType(type);
        mapper.insert(record);
        return record;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateStatus(ImportRecord importRecord) {
        ImportRecord update = new ImportRecord()
                .setStatus(importRecord.getStatus())
                .setTotalNum(importRecord.getTotalNum())
                .setFailureReason(importRecord.getFailureReason());
        mapper.update(update, Wrappers.<ImportRecord>lambdaUpdate()
                .eq(ImportRecord::getUuid, importRecord.getUuid()));
    }
}
