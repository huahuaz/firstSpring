package com.yc.biz;

import com.yc.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class HelloWorldTest {
    private ApplicationContext ac;

    @Before
    public void setUp() throws Exception {
        //ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        //AnnotationConfigApplicationContext 基于注解的配置容器类
        ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //读取 AppConfig.class  ->  basePackages = "com.yc"  ->  得到要扫描的路径
        //要检查这些包中的类上是否有@Component注解 如有、则实例化
        //存到一个Map<String,Object> === ac
    }

    @Test
    public void hello() {
        HelloWorld hw = (HelloWorld) ac.getBean("helloWorld");
        hw.hello();

        HelloWorld hw2 = (HelloWorld) ac.getBean("helloWorld");
        hw2.hello();

        //spring容器是单例模型
    }
}