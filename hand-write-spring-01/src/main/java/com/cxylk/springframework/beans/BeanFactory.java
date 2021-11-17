package com.cxylk.springframework.beans;

import com.cxylk.springframework.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean工厂，用于存放beanDefinition到map中
 * @author likui
 * @date 2021/11/16 4:00 下午
 **/
public class BeanFactory {
    /**
     * 用于存放beanDefinition的map，参考spring
     */
    Map<String, BeanDefinition> beanDefinitionMap=new ConcurrentHashMap<String, BeanDefinition>();

    /**
     * 一个spring容器最基本的功能，获取bean，参考spring
     * @param beanName beanID
     * @return bean实例
     */
    public Object getBean(String beanName){
        return beanDefinitionMap.get(beanName).getBean();
    }

    /**
     * 将beanDefinition注册到bdmp中
     * @param beanName beanID
     * @param beanDefinition bean定义
     */
    public void registerBeanDefinition(String beanName,BeanDefinition beanDefinition){
        beanDefinitionMap.put(beanName,beanDefinition);
    }
}
