package com.yyl.test;

import com.yyl.bean.User;
import com.yyl.config.spring.MyApplicationContext;
import com.yyl.config.spring.MyContextRefreshListener;
import com.yyl.interfaces.CloudServices;
import com.yyl.page.PageDto;
import com.yyl.page.PageInfo;
import com.yyl.service.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @Author: yang.yonglian
 * @CreateDate: 2019/12/18 10:32
 * @Version: 1.0
 */
@Slf4j
public class MyTest1 {
    @Test
    public void test1() throws Exception{

        new ClassPathXmlApplicationContext("spring.xml").start();
        try{
            //排除扫描bean的自定义注解测试
            MyApplicationContext.getBean(ExcludeService.class);
        }catch (NoSuchBeanDefinitionException e){
            log.debug("未查找到ExcludeService的bean的定义");
        }
        //添加扫描自定义注解的bean测试
        MyApplicationContext.getBean(IncludeService.class);
        //可以看到这里获取为空，说明，在xml配置的properties属性不是在
        //environment对象的propertyResolver属性中的
        String username = MyApplicationContext.getEnvironment().getProperty("spring.username");
        log.debug("xml property-placeholder property spring.username:{}",username);
        //xml形式配置properties文件
        PropertyPlaceholderBean propertyPlaceholderBean = MyApplicationContext.getBean(PropertyPlaceholderBean.class);
        log.debug("propertyPlaceholderBean:{}",propertyPlaceholderBean);
        //动态获取 <context:property-placeholder/>标签中的属性值
        PropertySourcesPlaceholderConfigurer pspc = MyApplicationContext.getBean(PropertySourcesPlaceholderConfigurer.class);
        //<context:property-placeholder/>标签的PropertySource的key 可查看
        //PropertySourcesPlaceholderConfigurer的源码
        String LOCAL_PROPERTIES_PROPERTY_SOURCE_NAME = "localProperties";
        PropertySource propertySource =   pspc.getAppliedPropertySources().get(LOCAL_PROPERTIES_PROPERTY_SOURCE_NAME);
        log.debug("springUserName:{}",propertySource.getProperty("spring.username"));
        //获取spring中缓存的RootBeanDefinition
        RootBeanDefinition aopServiceBeanDefinition = MyApplicationContext
                .getRootBeanDefinitionFromCache("yyl"+AopService.class.getName());
        Class cglibClazz = MyApplicationContext.getBean(AopService.class).getClass();
        String clazzName = MyApplicationContext.getBeanFactory().getType("yyl"+AopService.class.getName()).getName();
        //获取一个bean真正的类型，因为有可能这个bean被动态代理过，所以可以通过这种方式获取其真正的class
        Class clazz = aopServiceBeanDefinition.getTargetType();
        log.debug("aopService raw class is {},after cglib aop class is {}",clazz,cglibClazz);
        //可以看到这里的class也是经过cglib代理过的增强的class
        log.debug("beanFactoty get Type by name method :{} ",clazzName);
        //注解形式的aop启用
        Map<String,CloudServices> map = MyApplicationContext.getBeansOfType(CloudServices.class);
        log.info("cloudServices length:{}",map.size());
        log.info("cloudServices :{}",map);
        map.forEach((beanName,bean)->{
            log.info("beanName:{}",beanName);
            bean.callServer();
        });
        //测试什么接口都没实现的@Import的bean
        Arrays.stream(MyApplicationContext.getBeanNamesForType(ImportClass.class)).forEach(log::debug);
        //测试实现了ImportBeanDefinitionRegistrar接口的bean
        MyImportBeanDefinition myImportBeanDefinition = MyApplicationContext.getBean(MyImportBeanDefinition.class);
        log.debug("myImportBeanDefinition:{}",myImportBeanDefinition);
        //测试实现了DoSomethingService接口的bean,若存在除了BaseDoSomethingService的bean，就删除BaseDoSomethingService
        //否则使用BaseDoSomethingService，
        //自定义BeanPostProcessor，SaySomethingService类使用@MyPostConstruct实现类似@PostConstruct的功能
        MyApplicationContext.getBean(DoSomethingService.class).doSomething();
        //factoryBean测试
        MyFactoryBeanImpl impl1 = MyApplicationContext.getBean(MyFactoryBeanImpl.class);
        MyFactoryBeanImpl impl2 = MyApplicationContext.getBean(MyFactoryBeanImpl.class);
        MyFactoryBeanImpl impl3 = MyApplicationContext.getBean("yyl"+MyFactoryBeanService.class.getName());
        //factoryBean的getObject方法返回值是单例的，因为getObject方法只会执行一次
        //因为从FactoryBean获取Object方法的执行结果被缓存了
        log.debug("myFactoryBeanImpl is singleton?:{}",impl1==impl2);
        log.debug("myFactoryBeanImpl is singleton?:{}",impl1==impl3);
        //获取FactoryBean本身
        MyFactoryBeanService factoryBean1 = MyApplicationContext.getBean(MyFactoryBeanService.class);
        MyFactoryBeanService factoryBean2 = MyApplicationContext.getBean("&"+"yyl"+MyFactoryBeanService.class.getName());
        log.debug("factoryBean is singleton?:{}",factoryBean1 == factoryBean2);
        //spring容器事件发布，这里使用了提取ApplicationContext的静态方法
        //默认情况下监听的执行是同步的，查看源码可知，默认的SimpleApplicationEventMulticaster中的
        //taskExecutor属性为空，这里可以自定义
        MyApplicationContext.publishEvent(new MyApplicationEvent("1"));
        MyApplicationContext.publishEvent(new MyApplicationEvent("2"));
        //自定义ContextRefreshEvent的监听器，这个可用于监听spring容器是否启动完毕
        MyApplicationContext.getBean(MyContextRefreshListener.class);
        //spring aop 内部方法测试
        AopService aopService = MyApplicationContext.getBean(AopService.class);
        aopService.doWork("yyl");

    }

    /**
     * spring事务测试
     */
    @Test
    public void test2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = applicationContext.getBean(UserService.class);
        userService.insertByJdbcTemplate();
    }
    /**
     * mybatis分页查询
     */
    @Test
    public void test3(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = applicationContext.getBean(UserService.class);
        log.debug("find user:{}",userService.findAll());
        PageDto<User> pageDto = userService.findPage(new PageInfo(),"password2");
        log.debug("find user page total:{}",pageDto.getTotal());
        log.debug("find user page list:{}",pageDto);
    }

    /**
     * mybatis公用Service查询
     */
    @Test
    public void test4(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserServiceImpl userService = applicationContext.getBean(UserServiceImpl.class);
        User user = new User();
        user.setUserName("yyl11111");
        user.setPassword("12345678");
        userService.insert(user);

    }


}
