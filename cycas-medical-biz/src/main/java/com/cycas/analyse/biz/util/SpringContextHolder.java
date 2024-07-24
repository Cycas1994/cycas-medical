package com.cycas.analyse.biz.util;

import com.cycas.analyse.biz.enums.IllegalTypeEnum;
import com.cycas.analyse.biz.strategy.IllegalStrategy;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author xin.na
 * @since 2024/7/24 14:55
 */
@Slf4j
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicationContext未注入");
        }
        return applicationContext;
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> type) {
        Map<String, T> map = getApplicationContext().getBeansOfType(type);
        return map;
    }

    public static <T> List<T> getBeansOfTypeList(Class<T> type) {
        Map<String, T> map = getBeansOfType(type);
        if (CollectionUtils.isEmpty(map)) {
            return Collections.emptyList();
        }
        return Lists.newArrayList(map.values());
    }

    public static IllegalStrategy getIllegalStrategy(IllegalTypeEnum illegalType) {
        List<IllegalStrategy> strategyList = getBeansOfTypeList(IllegalStrategy.class);
        for (IllegalStrategy strategy : strategyList) {
            if (strategy.match(illegalType)) {
                return strategy;
            }
        }
        return null;
    }
}
