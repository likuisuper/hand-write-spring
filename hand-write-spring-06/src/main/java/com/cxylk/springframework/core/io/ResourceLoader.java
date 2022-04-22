package com.cxylk.springframework.core.io;

/**
 * @author likui
 * @date 2022/1/24 下午4:43
 * 用于加载资源（比如类路径、文件系统资源）的策略接口
 **/
public interface ResourceLoader {
    /** Pseudo URL prefix for loading from the class path: "classpath:". */
    public static final String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 返回指定location的Resource
     * @param location 资源位置
     * @return 相应资源
     */
    Resource getResource(String location);
}
