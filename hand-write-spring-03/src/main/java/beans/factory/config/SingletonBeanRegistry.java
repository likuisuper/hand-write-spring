package beans.factory.config;

import beans.factory.BeanFactory;

/**
 * 单例bean的注册接口，通过{@link BeanFactory}的实现来实现
 * @author likui
 * @date 2021/11/17 9:38 上午
 **/
public interface SingletonBeanRegistry {
    /**
     * 返回给定名称下注册的单例对象
     * @param beanName bean名称
     * @return 注册的单例对象
     */
    Object getSingleton(String beanName);
}
