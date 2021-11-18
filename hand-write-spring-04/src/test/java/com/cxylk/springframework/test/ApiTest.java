package com.cxylk.springframework.test;

import com.cxylk.springframework.beans.PropertyValue;
import com.cxylk.springframework.beans.PropertyValues;
import com.cxylk.springframework.beans.factory.config.BeanDefinition;
import com.cxylk.springframework.beans.factory.config.BeanReference;
import com.cxylk.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.cxylk.springframework.test.bean.UserDao;
import com.cxylk.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * @author likui
 * @date 2021/11/18 3:49 下午
 **/
public class ApiTest {
    @Test
    public void test(){
        //1、初始化bean工厂
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        //2、先注册userDao，因为UserService依赖它
        beanFactory.registryBeanDefinition("userDao", new BeanDefinition(UserDao.class));
        //3、UserService设置属性[uid,userDao]
        PropertyValues propertyValues=new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId","1000"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));
        //4、注册UserService
        BeanDefinition beanDefinition=new BeanDefinition(UserService.class,propertyValues);
        beanFactory.registryBeanDefinition("userService",beanDefinition);
        //5、获取bean
        UserService userService = (UserService)beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
