package com.yc.bean;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class HelloWorld {

    @PostConstruct
    public void setup(){
        System.out.println("MyPostConstruct");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("MyPreDestroy");
    }

    public HelloWorld(){
        System.out.println("hello world ");
    }

    public void show(){
        System.out.println("show");
    }

    /**
     * 根据类型查找，即HelloWorld查找beanMap
     * @param hello
     */
    //@MyAutowired
    //@Resource(name = "hw")
    public void setHello(HelloWorld hello){
        //System.out.println("@MyAutowired");
        System.out.println("@MyResource(name = \"hw\")");
    }
}
