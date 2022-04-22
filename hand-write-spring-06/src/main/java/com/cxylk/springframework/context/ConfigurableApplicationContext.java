package com.cxylk.springframework.context;

import com.cxylk.springframework.beans.BeansException;

/**
 * @author likui
 * @date 2022/1/25 下午8:13
 * 具有配置功能的应用程序上下文
 **/
public interface ConfigurableApplicationContext extends ApplicationContext{
    /**
     * 容器刷新入口
     * @throws BeansException
     * @throws IllegalStateException
     */
    void refresh() throws BeansException,IllegalStateException;
}
