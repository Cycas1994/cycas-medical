package com.cycas.analyse.biz.manager;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cycas.analyse.biz.entity.IllegalDetail;
import com.cycas.analyse.biz.mapper.IllegalDetailMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xin.na
 * @since 2024/7/24 11:11
 */
@Service
public class IllegalDetailManager {

    @Resource
    private IllegalDetailMapper mapper;

    public int deleteByImportUuid(String importUuid) {
        return mapper.delete(Wrappers.<IllegalDetail>lambdaQuery()
                .eq(IllegalDetail::getImportUuid, importUuid));
    }
}
