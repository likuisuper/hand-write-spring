package com.cxylk.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.cxylk.springframework.beans.BeansException;
import com.cxylk.springframework.beans.PropertyValue;
import com.cxylk.springframework.beans.PropertyValues;
import com.cxylk.springframework.beans.factory.config.BeanDefinition;
import com.cxylk.springframework.beans.factory.config.BeanReference;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * 具有自动注入能力的beanFactory
 * 在具有{@link AbstractBeanFactory}的能力的同时又实现了创建bean实例的功能
 *
 * @author likui
 * @date 2021/11/17 10:13 上午
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    Logger logger = LoggerFactory.getLogger(AbstractAutowireCapableBeanFactory.class);
    /**
     * 实例化策略，使用Cglib
     */
    InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            //通过构造函数创建实例，注意，这里默认调用的是无参构造函数！！！，所以如果bean中存在有参构造函数则会报错
            //所以后面需要考虑支持有参构造函数的情况
            bean = createBeanInstance(beanName, beanDefinition, args);
            //！！！属性填充！！！
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        //将实例化好的bean加入单例池
        addSingleton(beanName, bean);
        return bean;
    }

    /**
     * 属性填充
     *
     * @param beanName       bean名称
     * @param bean           bean实例
     * @param beanDefinition bean定义
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            if (Objects.isNull(propertyValues)) {
                logger.debug("The current bean has no attributes to be injected");
                return;
            }
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                //这里要考虑A依赖B，而B是引用类型的情况！！！
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    //触发当前bean依赖的bean的实例化
                    value = getBean(beanReference.getBeanName());
                }
                //属性填充
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values:" + beanName);
        }
    }

    /**
     * bean的实例化，这里采用Cglib策略
     *
     * @param beanName       bean名称
     * @param beanDefinition bean定义
     * @param args           支持带参构造函数
     * @return bean实例
     */
    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        //过滤接口，接口不能实例化
        if (beanClass.isInterface()) {
            throw new BeansException("Specified class is an interface");
        }
        //获取所有构造函数
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            //遍历构造函数，找到构造函数与入参信息args匹配的情况，这里只做了数量匹配
            //而在spring实现中，还需要做类型匹配，否则就算参数数量一样，类型不一样也会报错
            if (args != null && declaredConstructor.getParameterTypes().length == args.length) {
                constructorToUse = declaredConstructor;
                break;
            }
        }
        return this.getInstantiationStrategy().instantiate(beanName, beanDefinition, constructorToUse, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
