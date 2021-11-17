package com.cxylk.springframework.beans.factory;

import com.cxylk.springframework.beans.BeansException;
import com.cxylk.springframework.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean工厂，定义bean最基本的功能
 * @author likui
 * @date 2021/11/16 4:00 下午
 **/
public interface BeanFactory {

    /**
     * 一个spring容器最基本的功能，获取bean，参考spring
     * @param beanName beanID
     * @return bean实例
     * @throws BeansException 如果bean未找到
     */
    Object getBean(String beanName) throws BeansException;

}
