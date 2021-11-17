package beans.factory.support;

import beans.BeansException;
import beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 实例化策略接口，提供通用的实例化方法
 * 支持两种策略：1、JDK，2、Cglib
 *
 * @author likui
 * @date 2021/11/17 4:03 下午
 **/
public interface InstantiationStrategy {
    /**
     * 返回工厂中具有给定名称的bean实例
     *
     * @param beanName       bean名称
     * @param beanDefinition bean定义
     * @param ctor 构造函数
     * @param args 参数
     * @return bean实例
     * @throws BeansException if the instantiation attempt failed
     */
    Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor ctor, Object[] args) throws BeansException;
}
