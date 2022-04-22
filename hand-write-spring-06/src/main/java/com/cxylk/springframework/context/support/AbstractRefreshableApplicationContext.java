package com.cxylk.springframework.context.support;

import com.cxylk.springframework.beans.BeansException;
import com.cxylk.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.io.IOException;

/**
 * @author likui
 * @date 2022/1/25 下午8:29
 * 刷新bean工厂，子类需要唯一实现的是loadBeanDefinitions
 **/
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{
    @Override
    protected void refreshBeanFactory() throws BeansException, IllegalStateException {

    }

    /**
     * 资源加载
     * @param beanFactory
     * @throws BeansException
     * @throws IOException
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException, IOException;
}
