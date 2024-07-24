package com.cycas.analyse.biz.manager;

import com.cycas.analyse.biz.mapper.InHospitalMapper;
import com.cycas.analyse.biz.model.bo.IllegalParamBO;
import com.cycas.analyse.biz.model.dto.IllegalDetailDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xin.na
 * @since 2024/7/24 11:13
 */
@Service
public class InHospitalManager {

    @Resource
    private InHospitalMapper inHospitalMapper;

    public List<IllegalDetailDTO> listIllegalDetail(IllegalParamBO paramBO) {
        return inHospitalMapper.listIllegalDetail(paramBO);
    }

}
