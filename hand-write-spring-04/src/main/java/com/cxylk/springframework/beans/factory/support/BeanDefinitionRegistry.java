package com.cxylk.springframework.beans.factory.support;


import com.cxylk.springframework.beans.factory.config.BeanDefinition;

/**
 * 提供了BeanDefinition的注册功能
 * @author likui
 * @date 2021/11/17 10:41 上午
 **/
public interface BeanDefinitionRegistry {
    /**
     * 注册指定名称的beanDefinition
     * @param beanName bean名称
     * @param beanDefinition bean定义
     * @throws BeansException
     */
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
