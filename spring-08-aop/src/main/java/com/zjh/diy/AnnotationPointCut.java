package com.zjh.diy;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//标注类为切面
@Component
@Aspect
public class AnnotationPointCut {

    @Around("execution(* com.zjh.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("环绕前");
//        签名
        Signature signature = jp.getSignature();
        System.out.println(signature);
//        方法被真正调用时
        Object proceed = jp.proceed();
        System.out.println("环绕后");
    }

    @Before("execution(* com.zjh.service.UserServiceImpl.*(..))")
    public void before() {
        System.out.println("==========方法执行前==========");
    }

    @After("execution(* com.zjh.service.UserServiceImpl.*(..))")
    public void after() {
        System.out.println("==========方法执行后==========");
    }

}
