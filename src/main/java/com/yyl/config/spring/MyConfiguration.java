package com.yyl.config.spring;

import com.alibaba.druid.pool.DruidDataSource;
import com.yyl.annotation.EnableCustomMvcConfig;
import com.yyl.annotation.EnableImporteanDefinition;
import com.yyl.service.BeanService;
import com.yyl.service.ImportClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * @Description:
 * @Author: yang.yonglian
 * @CreateDate: 2019/12/18 10:50
 * @Version: 1.0
 */
@Configuration
//开启Aop功能
@EnableAspectJAutoProxy(exposeProxy = true)
//@Import导入Bean
@Import(ImportClass.class)
//通过@Import注册Bean
@EnableImporteanDefinition(name = "yyl222",age = "30")
//开启异步监听执行
//@EnableAsyncListener
//spring事务注解式配置
@EnableTransactionManagement(proxyTargetClass = true)
//注解形式开启spring-mvc配置，查看@EnableWebMvc源码可知
//对于mvc的拓展主要通过注册WebMvcConfigurer的实现类到spring中实现的
@EnableWebMvc
//自定义WebMvcConfigurer
@EnableCustomMvcConfig
public class MyConfiguration {
    @Bean
    public BeanService beanService(){
        return new BeanService();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }
    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }
    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
    @Bean
    public TransactionTemplate transactionTemplate(){
        return new TransactionTemplate(transactionManager());
    }

}
