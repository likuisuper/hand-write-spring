package com.cxylk.springframework.beans.factory.support;

import com.cxylk.springframework.beans.BeansException;
import com.cxylk.springframework.beans.factory.BeanFactory;
import com.cxylk.springframework.beans.factory.config.BeanDefinition;

import java.util.Objects;

/**
 * 抽象的bean工厂实现
 * 继承{@link DefaultSingletonBeanRegistry}，具备了使用单例bean注册的能力
 * 实现{@link BeanFactory}，具备了获取单例bean的能力，并且做了扩展
 * @author likui
 * @date 2021/11/17 9:51 上午
 **/
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String beanName) {
        Object singletonObject = getSingleton(beanName);
        //如果单例池中已经存在bean，直接返回
        if(Objects.nonNull(singletonObject)){
            return singletonObject;
        }
        //不存在，则需要创建单例bean
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName,beanDefinition);
    }

    /**
     * 根据指定名称获取BeanDefinition
     * @param beanName bean名称
     * @return 指定名称的beanDefinition
     * @throws BeansException in case of errors
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 参考spring实现，这里需要beanDefinition，通过上面方法得到
     * @param beanName bean名称
     * @param beanDefinition bean定义
     * @return bean的实例
     * @throws BeansException if the bean could not be created
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
