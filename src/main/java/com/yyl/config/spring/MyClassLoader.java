package com.yyl.config.spring;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * 自定义ClassLoader
 * @Author: yyl
 * @Date: 2019/3/11 14:22
 */
public class MyClassLoader extends URLClassLoader {
    public MyClassLoader(ClassLoader parent, URL... urls) {
        super(urls, parent);
    }
}
