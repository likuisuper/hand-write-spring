package com.cxylk.springframework.util;

/**
 * @author likui
 * @date 2022/1/24 下午4:19
 * 获取类加载器
 **/
public class ClassUtil {
    public static ClassLoader getDefaultClassLoader(){
        ClassLoader cl=null;
        try {
            cl=Thread.currentThread().getContextClassLoader();
        }catch (Throwable ex){
            // Cannot access thread context ClassLoader - falling back...
        }
        if(cl==null){
            // No thread context class loader -> use class loader of this class.
            cl=ClassUtil.class.getClassLoader();
        }
        return cl;
    }
}
