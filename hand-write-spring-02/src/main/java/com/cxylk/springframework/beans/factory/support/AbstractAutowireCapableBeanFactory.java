package com.cxylk.springframework.beans.factory.support;

import com.cxylk.springframework.beans.BeansException;
import com.cxylk.springframework.beans.factory.config.BeanDefinition;

/**
 * 具有自动注入能力的beanFactory
 * 在具有{@link AbstractBeanFactory}的能力的同时又实现了创建bean实例的功能
 * @author likui
 * @date 2021/11/17 10:13 上午
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean=null;
        try {
            //通过构造函数创建实例，注意，这里默认调用的是无参构造函数！！！，所以如果bean中存在有参构造函数则会报错
            //所以后面需要考虑支持有参构造函数的情况
            bean=beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed",e);
        }
        //将实例化好的bean加入单例池
        addSingleton(beanName,bean);
        return bean;
    }
}
