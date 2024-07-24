package com.cycas.analyse.biz.strategy;

import com.cycas.analyse.biz.entity.IllegalDetail;
import com.cycas.analyse.biz.enums.IllegalTypeEnum;
import com.cycas.analyse.biz.model.bo.IllegalParamBO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IllegalStrategy {

    IllegalTypeEnum illegalType();

    boolean match(IllegalTypeEnum illegalType);

    List<IllegalDetail> execute(IllegalParamBO paramBO);

}
