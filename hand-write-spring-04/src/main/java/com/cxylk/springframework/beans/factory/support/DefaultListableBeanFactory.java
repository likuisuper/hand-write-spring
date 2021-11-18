package com.cxylk.springframework.beans.factory.support;

import com.cxylk.springframework.beans.BeansException;
import com.cxylk.springframework.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 核心实现类，它具有最多的功能，在spring中是一个默认的容器
 * @author likui
 * @date 2021/11/17 10:32 上午
 **/
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry{
    /**
     * bdmp，用于存放beanDefinition
     */
    private final Map<String, BeanDefinition> beanDefinitionMap=new ConcurrentHashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition=this.beanDefinitionMap.get(beanName);
        if(Objects.isNull(beanDefinition)){
            throw new BeansException("No bean named '" + beanName + "'found in "+this);
        }
        return beanDefinition;
    }

    @Override
    public void registryBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanName,beanDefinition);
    }
}
