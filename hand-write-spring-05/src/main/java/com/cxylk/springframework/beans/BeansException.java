package com.cxylk.springframework.beans;

/**
 * Bean通用异常类
 * @author likui
 * @date 2021/11/16 11:33 下午
 **/
public class BeansException extends RuntimeException{

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
