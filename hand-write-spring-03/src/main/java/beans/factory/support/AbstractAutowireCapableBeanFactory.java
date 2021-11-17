package beans.factory.support;

import beans.BeansException;
import beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 具有自动注入能力的beanFactory
 * 在具有{@link AbstractBeanFactory}的能力的同时又实现了创建bean实例的功能
 * @author likui
 * @date 2021/11/17 10:13 上午
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    /**
     * 实例化策略，使用Cglib
     */
    InstantiationStrategy instantiationStrategy=new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition,Object[] args) throws BeansException {
        Object bean=null;
        try {
            //通过构造函数创建实例，注意，这里默认调用的是无参构造函数！！！，所以如果bean中存在有参构造函数则会报错
            //所以后面需要考虑支持有参构造函数的情况
            bean=createBeanInstance(beanName,beanDefinition,args);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed",e);
        }
        //将实例化好的bean加入单例池
        addSingleton(beanName,bean);
        return bean;
    }

    protected Object createBeanInstance(String beanName,BeanDefinition beanDefinition,Object[] args){
        Constructor constructorToUse=null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        //过滤接口，接口不能实例化
        if(beanClass.isInterface()){
            throw new BeansException("Specified class is an interface");
        }
        //获取所有构造函数
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            //遍历构造函数，找到构造函数与入参信息args匹配的情况，这里只做了数量匹配
            //而在spring实现中，还需要做类型匹配，否则就算参数数量一样，类型不一样也会报错
            if(args!=null&&declaredConstructor.getParameterTypes().length==args.length){
                constructorToUse=declaredConstructor;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanName,beanDefinition,constructorToUse,args);
    }
}
