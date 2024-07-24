package com.cycas.analyse.biz.strategy;

import com.cycas.analyse.biz.entity.IllegalDetail;
import com.cycas.analyse.biz.enums.IllegalTypeEnum;
import com.cycas.analyse.biz.model.bo.IllegalParamBO;
import com.cycas.analyse.biz.util.SpringContextHolder;
import com.cycas.common.biz.util.AssertUtil;

import java.util.List;

/**
 * @author xin.na
 * @since 2024/7/24 14:44
 */
public class IllegalStrategyContext {

    private IllegalStrategy illegalStrategy;

    public IllegalStrategyContext(IllegalTypeEnum illegalType) {
        AssertUtil.notNull(illegalType, "违规策略不能为空");
        this.illegalStrategy = SpringContextHolder.getIllegalStrategy(illegalType);
    }

    public List<IllegalDetail> execute(IllegalParamBO paramBO) {
        return this.illegalStrategy.execute(paramBO);
    }
}
