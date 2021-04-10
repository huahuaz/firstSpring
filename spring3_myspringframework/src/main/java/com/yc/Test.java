package com.yc;

import com.yc.bean.HelloWorld;
import com.yc.biz.StudentBizImpl;
import com.yc.springframework.context.MyAnnotationConfigApplicationContext;
import com.yc.springframework.context.MyApplicationContext;

public class Test {
    public static void main(String[] args) {
        MyApplicationContext ac = new MyAnnotationConfigApplicationContext(MyAppConfig.class);
//        HelloWorld hw = (HelloWorld) ac.getBean("hw");
//        hw.show();
        StudentBizImpl sb = (StudentBizImpl) ac.getBean("studentBizImpl");
        sb.add("aaa");
    }
}
