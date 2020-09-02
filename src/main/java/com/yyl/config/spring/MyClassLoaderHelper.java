package com.yyl.config.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URLClassLoader;

/**
 * @Author: yyl
 * @Date: 2019/3/11 14:19
 */
public class
MyClassLoaderHelper {
    private static Logger logger = LoggerFactory.getLogger(MyClassLoaderHelper.class);
    public static URLClassLoader getCustomerClassLoader(File configFile) {
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            if (currentClassLoader instanceof URLClassLoader) {
                MyClassLoader myClassLoader = new MyClassLoader(currentClassLoader, configFile.toURI().toURL());
                return myClassLoader;
            }
        } catch (Exception e) {
            logger.error("create customer classloader fail");
        }
        return (URLClassLoader) currentClassLoader;
    }
}
