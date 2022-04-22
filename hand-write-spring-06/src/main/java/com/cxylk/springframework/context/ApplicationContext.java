package com.cxylk.springframework.context;

import com.cxylk.springframework.beans.factory.ListableBeanFactory;

/**
 * @author likui
 * @date 2022/1/25 下午8:06
 * 为应用程序提供配置的中央接口。它应该支持：
 * 1、加载文件资源的能力
 * 2、向注册的监听器发布事件的能力
 * 3、解决消息的能力，支持国际化
 **/
public interface ApplicationContext extends ListableBeanFactory {
}
