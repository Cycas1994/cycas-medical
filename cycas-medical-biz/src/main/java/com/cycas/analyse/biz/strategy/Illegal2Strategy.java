package com.cycas.analyse.biz.strategy;

import com.cycas.analyse.biz.entity.IllegalDetail;
import com.cycas.analyse.biz.enums.IllegalTypeEnum;
import com.cycas.analyse.biz.manager.IllegalDetailManager;
import com.cycas.analyse.biz.manager.InHospitalManager;
import com.cycas.analyse.biz.model.bo.IllegalParamBO;
import com.cycas.analyse.biz.model.dto.IllegalDetailDTO;
import com.cycas.analyse.biz.service.IllegalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.cycas.analyse.biz.enums.IllegalTypeEnum.ZHU_SHE_YONG_AN_QU_NAN;

/**
 * 注射用氨曲南策略
 * @author xin.na
 * @since 2024/7/24 10:36
 */
@Service
public class Illegal2Strategy implements IllegalStrategy {

    @Autowired
    private InHospitalManager inHospitalManager;
    @Autowired
    private IllegalDetailManager illegalDetailManager;
    @Autowired
    private IllegalDetailService illegalDetailService;

    @Override
    public IllegalTypeEnum illegalType() {
        return ZHU_SHE_YONG_AN_QU_NAN;
    }

    @Override
    public boolean match(IllegalTypeEnum illegalType) {
        return this.illegalType().equals(illegalType);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<IllegalDetail> execute(IllegalParamBO paramBO) {
        illegalDetailManager.deleteByImportUuid(paramBO.getImportUuid());
        List<IllegalDetailDTO> dtoList = inHospitalManager.listIllegalDetail(paramBO);
        List<IllegalDetail> list = dtoList.stream().map(dto -> {
            IllegalDetail detail = IllegalDetail.from(dto);
            detail.setImportUuid(paramBO.getImportUuid());
            detail.setType(this.illegalType().getType());
            return detail;
        }).collect(Collectors.toList());
        illegalDetailService.saveBatch(list);
        return list;
    }
}
