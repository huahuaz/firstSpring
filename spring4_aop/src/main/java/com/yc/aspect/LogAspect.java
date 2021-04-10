package com.yc.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect     //切面类: 你要增强的功能写到这里
@Component   //IOC注解， 以实现让spring托管的功能
@Order(value = 1)
public class LogAspect {

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

    //切入点表达式的语法: ?代表出现0次或一次
    //modifiers-pattern:修饰衔
    //ret-type-pattern:返回类型
    //declaring-type-pattern:
    //name-pattern:名字模型
    //throws-pattern
    //execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern)
    //          throws-pattern?)

    //增加的声明
    @Before("com.yc.aspect.LogAspect.add()")
    public void log() {
        System.out.println("=======前置增强的日志=======");
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dstr = sdf.format(d);
        System.out.println("执行时间为:" + dstr);
        System.out.println("=======前置增强的日志结束=======");
    }

    @After("com.yc.aspect.LogAspect.addAndUpdate()")
    public void bye(JoinPoint jp) {    //spring是一个ioc容器，它可以使用di将程序运行的信息注入 joinpoint
        System.out.println("===========bye=========");
        //连接点中的所有信息
        Object target = jp.getTarget();
        System.out.println("目标类为:" + target);

        Object method = jp.getSignature();
        System.out.println("方法:" + method);

        Object[] objs = jp.getArgs();
        if (objs != null) {
            for (Object o : objs) {
                System.out.println("参数:" + o);
            }
        }
        System.out.println("=========bye=========");
    }

    @Around("find()")
    public Object compute(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("进到增强了...");
        long start = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("find运行时间:" + (end - start));
        System.out.println("退出增强了...");
        return retVal;
    }
}
