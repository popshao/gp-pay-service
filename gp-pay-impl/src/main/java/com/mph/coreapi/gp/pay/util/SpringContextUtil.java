package com.mph.coreapi.gp.pay.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * Created by lishaoyong on 17/5/23.
 * 获取spring的上下文信息
 */
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        return (T) applicationContext.getBean(name);
    }

}
