package com.cxylk.springframework.beans.factory;


import com.cxylk.springframework.beans.BeansException;

/**
 * Bean工厂，定义bean最基本的功能
 * @author likui
 * @date 2021/11/16 4:00 下午
 **/
public interface BeanFactory {

    /**
     * 一个spring容器最基本的功能，获取bean，参考spring
     * @param name bean名称
     * @return bean实例
     * @throws BeansException if the bean could not be created
     */
    Object getBean(String name) throws BeansException;

    /**
     * 支持根据构造函数参数来获取bean
     * @param name bean名称
     * @param args 使用显示参数创建bean实例时使用的参数
     * @return bean实例
     * @throws BeansException if the bean could not be created
     */
    Object getBean(String name, Object... args) throws BeansException;

    /**
     * 根据名称和类型返回指定的bean
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> T getBean(String name,Class<T> requiredType) throws BeansException;
}
