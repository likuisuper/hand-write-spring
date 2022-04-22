package com.cxylk.springframework.beans.factory.support;

import com.cxylk.springframework.beans.BeansException;
import com.cxylk.springframework.core.io.Resource;
import com.cxylk.springframework.core.io.ResourceLoader;

/**
 * @author likui
 * @date 2022/1/24 下午4:54
 * 读取BeanDefinition
 **/
public interface BeanDefinitionReader {
    /**
     * 返回bean工厂以注册BeanDefinition
     * @return bean工厂，这里默认是{@link DefaultListableBeanFactory}
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 返回资源加载器以加载资源
     * @return
     */
    ResourceLoader getResourceLoader();

    /**
     * 从指定的resource加载BeanDefinition
     * @param resource 资源
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    /**
     * 从指定的location加载BeanDefinition
     * @param location 资源位置
     */
    void loadBeanDefinitions(String location) throws BeansException;

    /**
     * 从指定的多个资源加载BeanDefinition
     * @param resource 资源
     */
    void loadBeanDefinitions(Resource... resources) throws BeansException;
}
