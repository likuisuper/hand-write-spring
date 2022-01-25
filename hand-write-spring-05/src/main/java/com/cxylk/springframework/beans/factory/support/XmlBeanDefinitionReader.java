package com.cxylk.springframework.beans.factory.support;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.cxylk.springframework.beans.BeansException;
import com.cxylk.springframework.beans.PropertyValue;
import com.cxylk.springframework.beans.factory.config.BeanDefinition;
import com.cxylk.springframework.beans.factory.config.BeanReference;
import com.cxylk.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author likui
 * @date 2022/1/24 下午5:21
 * 读取xml的BeanDefinition
 **/
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry){
        super(registry);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try(InputStream inputStream = resource.getInputStream()){
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from " + resource,e);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException{
        Resource resource = getResourceLoader().getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException{
        Assert.notNull(resources, "Resource array must not be null");
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    /**
     * 实际上从指定的XML文件加载bean定义
     * @param inputStream 要从中读取的输入流
     * @throws BeansException
     */
    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        //这里使用hutool提供的工具解析xml，底层使用的是jdk提供的w3c工具
        Document document = XmlUtil.readXML(inputStream);
        Element element = document.getDocumentElement();
        //获取子节点集合
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            //判断元素
            if(!(childNodes.item(i) instanceof Element)) continue;
            //判断对象
            if(!"bean".equals(childNodes.item(i).getNodeName())) continue;

            //解析标签(bean标签)
            Element bean=(Element)childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            //获取class，方便获取类中的名称
            Class<?> clazz = Class.forName(className);
            //优先级 id>name
            String beanName= StrUtil.isNotEmpty(id)?id:name;
            if(StrUtil.isEmpty(beanName)){
                beanName=StrUtil.lowerFirst(clazz.getSimpleName());
            }

            //定义bean
            BeanDefinition beanDefinition=new BeanDefinition(clazz);
            //读取属性并填充
            for(int j=0;j<bean.getChildNodes().getLength();j++){
                //判断元素
                if(!(bean.getChildNodes().item(j) instanceof Element)) continue;
                //判断属性
                if(!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;

                //解析标签 property
                Element property = (Element)bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef=property.getAttribute("ref");
                //获取属性值，引用类型，值类型
                Object value=StrUtil.isNotEmpty(attrRef)?new BeanReference(attrRef):attrValue;
                //创建属性信息
                PropertyValue propertyValue=new PropertyValue(attrName,value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            //判断beanName是否重复
            if(getRegistry().containsBeanDefinition(beanName)){
                throw new BeansException("Duplicate beanName["+beanName+"] is not allowed");
            }
            //注册BeanDefinition
            getRegistry().registryBeanDefinition(beanName,beanDefinition);
        }
    }
}
