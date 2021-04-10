package com.yc.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect     //切面类: 你要增强的功能写到这里
@Component   //IOC注解， 以实现让spring托管的功能
@Order(value = 10)
public class Log2Aspect {

    //切入点的声明  pointcut signature
    @Pointcut("execution(* com.yc.biz.StudentBizImpl.add*(..))") //切入点表达式:哪些方法上加增强
    private void add() {
    }

    @Pointcut("execution(* com.yc.biz.StudentBizImpl.update*(..))") //切入点表达式:哪些方法上加增强
    private void update() {
    }

    @Pointcut("execution(* com.yc.biz.StudentBizImpl.find*(..))")//切入点表达式:哪些方法上加增强
    private void find() {

    }

    @Pointcut("add() || update()")
    private void addAndUpdate() {
    }

    //增加的声明
    @Before("com.yc.aspect.Log2Aspect.add()")
    public void log(){
        System.out.println("=======前置增强的日志=======");
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dstr = sdf.format(d);
        System.out.println("执行时间为:"+dstr);
        System.out.println("=======前置增强的日志结束=======");
        System.out.println("=========log2=========");
    }
}
