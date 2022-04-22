package com.cxylk.springframework.test;

import cn.hutool.core.io.IoUtil;
import com.cxylk.springframework.beans.PropertyValue;
import com.cxylk.springframework.beans.PropertyValues;
import com.cxylk.springframework.beans.factory.config.BeanDefinition;
import com.cxylk.springframework.beans.factory.config.BeanReference;
import com.cxylk.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.cxylk.springframework.beans.factory.support.XmlBeanDefinitionReader;
import com.cxylk.springframework.core.io.ClassPathResource;
import com.cxylk.springframework.core.io.DefaultResourceLoader;
import com.cxylk.springframework.core.io.Resource;
import com.cxylk.springframework.test.bean.UserDao;
import com.cxylk.springframework.test.bean.UserService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author likui
 * @date 2021/11/18 3:49 下午
 **/
public class ApiTest {
    @Test
    public void test1(){
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



    private DefaultResourceLoader resourceLoader;

    @Before
    public void init(){
        resourceLoader=new DefaultResourceLoader();
    }

    /**
     * 测试加载classpath
     * @throws IOException
     */
    @Test
    public void testClassPath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:import.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    /**
     * 测试加载filesystem
     * @throws IOException
     */
    @Test
    public void testFileSystem() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/import.properties");
        InputStream inputStream = resource.getInputStream();
        String s = IoUtil.readUtf8(inputStream);
        System.out.println(s);
    }

    /**
     * 测试加载URL
     */
    @Test
    public void testUrl() throws IOException {
        Resource resource = resourceLoader.getResource("https://github.com/likuisuper/hand-write-spring/tree/main/hand-write-spring-05/src/test/resources/import.properties");
        InputStream inputStream = resource.getInputStream();
        String s = IoUtil.readUtf8(inputStream);
        System.out.println(s);
    }

    /**
     * 将test1中的2、3、4步简化，不需要手动去注册BeanDefinition
     */
    @Test
    public void test2(){
        //1、初始化bean工厂
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        //2、初始化BeanDefinitionReader，这里传入的是beanFactory，因为它实现了BeanDefinitionRegistry，
        //而我们需要使用到registryBeanDefinition这个方法
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        //3、读取spring.xml配置
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");
        //4、获取bean
        UserService userService = beanFactory.getBean("userService",UserService.class);
        userService.queryUserInfo();
    }
}
