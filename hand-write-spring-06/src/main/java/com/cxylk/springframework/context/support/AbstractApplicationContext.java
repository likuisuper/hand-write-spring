package com.cxylk.springframework.context.support;

import com.cxylk.springframework.beans.BeansException;
import com.cxylk.springframework.context.ApplicationContext;
import com.cxylk.springframework.context.ConfigurableApplicationContext;

/**
 * @author likui
 * @date 2022/1/25 下午8:09
 * 应用上下文的抽象类，简单实现通用的上下文功能。采用模板设计模式，抽象方法由具体的子类实现
 **/
public abstract class AbstractApplicationContext implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException, IllegalStateException {
        refreshBeanFactory();
    }

    /**
     * 刷新bean工厂
     * @throws BeansException 如果bean工厂初始化失败
     * @throws IllegalStateException 如果已经初始化并且不支持多次刷新
     */
    protected abstract void refreshBeanFactory() throws BeansException,IllegalStateException;
}
