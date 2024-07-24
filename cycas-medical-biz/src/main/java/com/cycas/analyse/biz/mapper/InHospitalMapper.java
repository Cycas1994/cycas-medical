package com.cycas.analyse.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cycas.analyse.biz.entity.InHospital;
import com.cycas.analyse.biz.model.bo.IllegalParamBO;
import com.cycas.analyse.biz.model.dto.IllegalDetailDTO;

import java.util.List;

public interface InHospitalMapper extends BaseMapper<InHospital> {

    List<IllegalDetailDTO> listIllegalDetail(IllegalParamBO paramBO);

}
