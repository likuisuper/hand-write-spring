package com.cxylk.springframework.beans;

/**
 * 用于保存单个bean属性的信息的值的对象
 * @author likui
 * @date 2021/11/18 3:01 下午
 **/
public class PropertyValue {
    private String name;

    private Object value;

    public PropertyValue() {
    }

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
