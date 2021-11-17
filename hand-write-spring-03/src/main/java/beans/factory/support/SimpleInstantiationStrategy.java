package beans.factory.support;

import beans.BeansException;
import beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 简单实例化策略，使用JDK实现
 * @author likui
 * @date 2021/11/17 4:06 下午
 **/
public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor ctor,Object[] args) throws BeansException {
        Class<?> beanClass = beanDefinition.getBeanClass();
        try {
            if(ctor!=null){
                return beanClass.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            }else {
                //为空则使用无参构造函数实例化
                return beanClass.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new BeansException("Failed to instantiate ["+beanClass.getName()+"]",e);
        }
    }
}
