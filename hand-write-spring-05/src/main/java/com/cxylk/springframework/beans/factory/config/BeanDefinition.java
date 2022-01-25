package com.cxylk.springframework.beans.factory.config;

import com.cxylk.springframework.beans.PropertyValues;

/**
 * 用于定义bean实例化信息，将Object类型换成Class类型，
 * 为了将bean的实例化操作交给容器来处理，而不是通过构造函数
 * @author likui
 * @date 2021/11/16 3:59 下午
 **/
public class BeanDefinition {
    private Class<?> beanClass;

    /**
     * 用于属性注入
     */
    private PropertyValues propertyValues;

    public BeanDefinition(Class<?> beanClass) {
        this(beanClass,null);
    }

    /**
     * 支持属性注入的构造方法
     * @param beanClass
     * @param propertyValues
     */
    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues!=null?propertyValues:new PropertyValues();
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
