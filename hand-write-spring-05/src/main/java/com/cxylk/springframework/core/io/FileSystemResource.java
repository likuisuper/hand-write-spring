package com.cxylk.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author likui
 * @date 2022/1/24 下午4:27
 * 文件系统的资源实现
 **/
public class FileSystemResource implements Resource{
    private final File file;

    private final String path;

    public FileSystemResource(String path) {
        Assert.notNull(path, "Path must not be null");
        this.path = path;
        this.file=new File(path);
    }

    public FileSystemResource(File file) {
        Assert.notNull(file, "File must not be null");
        this.file = file;
        this.path=file.getPath();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }
}
