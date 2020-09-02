package com.shardingjdbc.configuration;

import io.shardingsphere.core.keygen.DefaultKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: yyl
 * @Date: 2019/2/20 19:36
 */
@Configuration
public class MyConfiguration {
    @Bean
    public DefaultKeyGenerator defaultKeyGenerator(){
        return new DefaultKeyGenerator();
    }
}
