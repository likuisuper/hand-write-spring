package com.cxylk.springframework.test;

import bean.UserService;
import com.cxylk.springframework.beans.factory.config.BeanDefinition;
import com.cxylk.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * @author likui
 * @date 2021/11/17 10:48 上午
 **/
public class ApiTest {
    @Test
    public void test(){
        //1、初始化bean工厂
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        //2、注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registryBeanDefinition("userService",beanDefinition);
        //第一次获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
        //第二次获取bean(直接从单例池中获取)
        UserService userService1 = (UserService) beanFactory.getBean("userService");
        userService1.queryUserInfo();
    }
}
