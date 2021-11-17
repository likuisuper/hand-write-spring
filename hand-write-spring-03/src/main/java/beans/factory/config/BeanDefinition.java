package beans.factory.config;

/**
 * 用于定义bean实例化信息，将Object类型换成Class类型，
 * 为了将bean的实例化操作交给容器来处理，而不是通过构造函数
 * @author likui
 * @date 2021/11/16 3:59 下午
 **/
public class BeanDefinition {
    private Class<?> beanClass;

    public BeanDefinition(Class<?> beanClass) {
        this.beanClass=beanClass;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }
}
