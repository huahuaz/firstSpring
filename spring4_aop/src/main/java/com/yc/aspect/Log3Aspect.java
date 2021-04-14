package com.yc.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect     //切面类: 你要增强的功能写到这里
@Component   //IOC注解， 以实现让spring托管的功能
@Order(value = 1)
public class Log3Aspect {

    @Around("execution(* com.yc.biz.StudentBizImpl.find*(..))")
    public Object compute2(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("********compute2****进到增强了...");
        long start = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("*******compute2退出增强了...");
        System.out.println("find运行时间:" + (end - start));
        return retVal;
    }
}
