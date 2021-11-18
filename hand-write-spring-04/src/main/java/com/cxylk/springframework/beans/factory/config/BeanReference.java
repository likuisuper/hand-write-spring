package com.cxylk.springframework.beans.factory.config;

/**
 * Bean的引用，解决Bean所依赖的属性是引用类型的问题
 * @author likui
 * @date 2021/11/18 3:39 下午
 **/
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
