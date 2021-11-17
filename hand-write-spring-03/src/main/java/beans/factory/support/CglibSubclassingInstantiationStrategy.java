package beans.factory.support;

import beans.BeansException;
import beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * Cglib实现的实例化策略。
 * <br>在spring中，它是继承了{@link SimpleInstantiationStrategy}，这里为了简单，直接实现了{@link InstantiationStrategy}</br>
 * @author likui
 * @date 2021/11/17 4:25 下午
 **/
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor ctor, Object[] args) throws BeansException {
        Enhancer enhancer=new Enhancer();
        //动态生成子类
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if(ctor==null){
            //使用超类的无参构造函数创建
            return enhancer.create();
        }
        //使用指定的超类的带参构造函数创建
        return enhancer.create(ctor.getParameterTypes(),args);
    }
}
