package com.yyl.config.spring;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.io.File;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yyl
 * @Date: 2019/3/11 10:04
 */
public class MyContextLoaderListener extends ContextLoaderListener {
    private Logger log = LoggerFactory.getLogger(MyContextLoaderListener.class);

    private String YYL_HOME = "yyl_home";


    /**
     * 添加自定义Properties进Environemnt对象
     * @param sc
     * @param wac
     */
    @Override
    protected void customizeContext(ServletContext sc, ConfigurableWebApplicationContext wac) {
        super.customizeContext(sc, wac);
        Map map = new HashMap();
        map.put("cloud.version","2.0.0");
        Environment environment = wac.getEnvironment();
        ((ConfigurableEnvironment) environment).getPropertySources().addLast(new MapPropertySource("customerProperties",map));
    }


    /**
     * 自定义配置文件目录，当当前项目下存在对应文件时会先去加载当前目录下的文件
     * 如果不存在，则从我们自定义的配置文件目录中加载
     * @param event
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        String userHome = System.getProperty(YYL_HOME);
        String separator = System.getProperty("file.separator");
        if(StringUtils.isEmpty(userHome)){
            log.debug(YYL_HOME+" is empty");
        }else{
            //获取应用名称
            String appName = event.getServletContext().getContextPath();
            if(appName.startsWith("/")){
                appName = appName.substring(1);
            }
            String fullConfigFilePath = userHome+separator+appName+separator+"conf";
            File file = new File(fullConfigFilePath);
            if(file.exists()){
                URLClassLoader classLoader = MyClassLoaderHelper.getCustomerClassLoader(file);
                Thread.currentThread().setContextClassLoader(classLoader);
            }else{
                log.debug("config file is not exist");
            }
        }
        super.contextInitialized(event);
    }

}
