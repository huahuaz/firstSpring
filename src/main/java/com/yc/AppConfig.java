
package com.yc;

import com.yc.biz.StudentBizImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration  //表示当前的类是一个配置类
@ComponentScan(basePackages = "com.yc")  //将来要托管的bean要扫描的包及子包
public class AppConfig {    //java的容器配置

    //bean容器
    @Bean
    public StudentBizImpl studentBizImpl() {
        return new StudentBizImpl();
    }
}