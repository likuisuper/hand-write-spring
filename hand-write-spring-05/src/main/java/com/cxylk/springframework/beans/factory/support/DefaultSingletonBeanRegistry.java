package com.cxylk.springframework.beans.factory.support;


import com.cxylk.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link SingletonBeanRegistry}的默认实现
 * @author likui
 * @date 2021/11/17 9:42 上午
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    /**
     * 单例池
     */
    private final Map<String,Object> singletonObjects=new ConcurrentHashMap<String, Object>(256);

    /**
     * 根据指定名称获取单例bean
     * @param beanName bean名称
     * @return 单例bean
     */
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * 注册单例bean，参考spring，暂时不考虑线程安全
     * @param beanName bean名称
     * @param singletonObject 单例bean
     */
    protected void addSingleton(String beanName,Object singletonObject){
        this.singletonObjects.put(beanName,singletonObject);
    }
}
