package com.cxylk.springframework.test;

import com.cxylk.springframework.beans.factory.BeanFactory;
import com.cxylk.springframework.beans.factory.config.BeanDefinition;
import com.cxylk.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * @author likui
 * @date 2021/11/16 4:10 下午
 **/
public class ApiTest {
    @Test
    public void test_beanFactory(){
        //1、初始化bean容器
        BeanFactory beanFactory=new BeanFactory();

        //2、将beanDefinition注册到容器中
        BeanDefinition beanDefinition=new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        //3、获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
