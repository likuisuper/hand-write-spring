package com.cxylk.springframework.test;

import bean.UserService;
import beans.factory.config.BeanDefinition;
import beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

import java.io.IOException;

/**
 * @author likui
 * @date 2021/11/17 3:18 下午
 **/
public class ApiTest {
//    @Test
    public void test() throws IOException {
        //1、初始化bean工厂
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        //2、注册bean信息
        BeanDefinition beanDefinition=new BeanDefinition(UserService.class);
        beanFactory.registryBeanDefinition("userService",beanDefinition);
        //3、获取bean
        UserService userService = (UserService) beanFactory.getBean("userService","cxylk");
        userService.queryUserInfo();
        //再次获取bean
        UserService userService1 = (UserService) beanFactory.getBean("userService","cxylk");
        userService1.queryUserInfo();
        System.in.read();
    }

    public static void main(String[] args) throws IOException {
        //1、初始化bean工厂
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        //2、注册bean信息
        BeanDefinition beanDefinition=new BeanDefinition(UserService.class);
        beanFactory.registryBeanDefinition("userService",beanDefinition);
        //3、获取bean
        UserService userService = (UserService) beanFactory.getBean("userService","cxylk");
        userService.queryUserInfo();
        //再次获取bean
        UserService userService1 = (UserService) beanFactory.getBean("userService","cxylk");
        userService1.queryUserInfo();
        System.in.read();
    }
}
