package com.cxylk.springframework.beans;

import com.cxylk.springframework.beans.PropertyValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 包含一个或多个propertyValue的持有者
 * @author likui
 * @date 2021/11/18 3:09 下午
 **/
public class PropertyValues {
    /**
     * 用于保存propertyValue的集合
     */
    private final List<PropertyValue> propertyValues=new ArrayList<>();

    /**
     * 添加属性对象到属性集合中
     * @param propertyValue 用于保存bean属性信息的值的对象
     */
    public void addPropertyValue(PropertyValue propertyValue){
        this.propertyValues.add(propertyValue);
    }

    /**
     * 返回集合中保存的property对象的数组
     * @return propertyValue数组
     */
    public PropertyValue[] getPropertyValues(){
        return this.propertyValues.toArray(new PropertyValue[0]);
    }

    /**
     * 返回指定属性名称的propertyValue
     * @param propertyName 属性名称
     * @return propertyValue
     */
    public PropertyValue getPropertyValue(String propertyName){
        for (PropertyValue propertyValue : propertyValues) {
            if(propertyValue.getName().equals(propertyName)){
                return propertyValue;
            }
        }
        return null;
    }
}
