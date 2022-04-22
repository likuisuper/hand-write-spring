package com.cxylk.springframework.core.io;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author likui
 * @date 2022/1/24 下午4:48
 * 默认的资源加载实现
 **/
public class DefaultResourceLoader implements ResourceLoader{

    @Override
    public Resource getResource(String location) {
        //处理类路径的资源
        if(location.startsWith(CLASSPATH_URL_PREFIX)){
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }else {
            try {
                //处理URL资源
                URL url=new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                //处理文件系统资源
                return new FileSystemResource(location);
            }

        }
    }
}
