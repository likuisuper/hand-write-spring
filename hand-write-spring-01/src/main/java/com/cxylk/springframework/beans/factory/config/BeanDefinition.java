package com.cxylk.springframework.beans.factory.config;

/**
 * 用于定义bean实例化信息，当前实现是以一个object存放对象
 * @author likui
 * @date 2021/11/16 3:59 下午
 **/
public class BeanDefinition {
    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean=bean;
    }

    public Object getBean() {
        return bean;
    }
}
