package com.yc.springboot.helloworld.controller;

import com.yc.springboot.helloworld.properties.YcProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController    //@Controller控制层  @Restful 以rest规范(请求方式:get,post,put,delete，   json)发送请求和响应
public class HelloWorldController {
    //创建日志对象
    private static Log log = LogFactory.getLog(HelloWorldController.class);

    @Autowired
    private Environment env;

    @Value("${yc.tname}")
    private String tname;

    @Autowired
    private YcProperties yp;

    @GetMapping("/hello")   //请求方式:get  请求路径：/hello
    public String hello(@RequestParam(value = "name", defaultValue = "瓜皮娃子") String name) { //@RequestParam请求的参数name
        log.debug("======debug======");
        log.info("=======info=======");
        log.error("=====error=======");
        log.fatal("=======fatal=====");

        log.info("系统环境变量信息如下：" + env);
        log.info("用户路径:" + env.getProperty("user.home"));
        log.info("----" + env.getProperty("JAVA_HOME"));
        log.info("yc.tname:" + tname);
        log.info("YcProperties中的属性:" + yp.getTname() + "\t" + yp.getAge());
        log.info("env获取上面属性:" + env.getProperty("yc.tname") + "\t" + env.getProperty("yc.age"));
        return String.format("Hello %s!", name);
    }

    @GetMapping("/hello2")   //请求方式:get  请求路径：/hello
    public String hello2(@RequestParam(value = "name", defaultValue = "瓜皮娃子") String name) { //@RequestParam请求的参数name
        return String.format("Hello %s!", name);
    }

    @GetMapping("/hello3")   //请求方式:get  请求路径：/hello
    public String hello3(@RequestParam(value = "name", defaultValue = "瓜皮娃子") String name) { //@RequestParam请求的参数name
        return String.format("Hello %s!", name);
    }
}
