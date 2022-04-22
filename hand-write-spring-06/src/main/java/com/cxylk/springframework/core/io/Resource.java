package com.cxylk.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author likui
 * @date 2022/1/24 下午4:09
 * 提供getInputStream操作，注意，在spring的实现中，该方法实际是InputStreamSource接口定义的，
 * 这里简化实现
 **/
public interface Resource {
    /**
     * 获取输入流
     * @return 输入流
     * @throws IOException 当无法获取流时
     */
    InputStream getInputStream() throws IOException;
}
