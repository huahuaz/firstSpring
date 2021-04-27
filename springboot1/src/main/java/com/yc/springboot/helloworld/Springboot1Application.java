package com.yc.springboot.helloworld;

import com.yc.springboot.helloworld.properties.YcProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@RestController    //@Controller控制层  @Restful 以rest规范(请求方式:get,post,put,delete，   json)发送请求和响应
@EnableConfigurationProperties(YcProperties.class)
public class Springboot1Application extends SpringBootServletInitializer {

    private static Log log = LogFactory.getLog(Springboot1Application.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        log.info("程序 启动了");
        return application.sources(Springboot1Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Springboot1Application.class, args);
    }

    /*@GetMapping("/hello")   //请求方式:get  请求路径：/hello
    public String hello(@RequestParam(value = "name", defaultValue = "瓜皮娃子") String name) { //@RequestParam请求的参数name
        return String.format("Hello %s!", name);
    }*/
}
