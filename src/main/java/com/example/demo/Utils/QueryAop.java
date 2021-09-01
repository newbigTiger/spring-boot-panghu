package com.example.demo.Utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 胖虎
 * @date 2021/4/6 下午 4:07
 */
@Aspect
@Component
public class QueryAop {

    @Pointcut("within(*com.example.demo.controller ..*)")
    public void find(){

    }
    @Before("find()")
    public void beforeQuery(JoinPoint joinPoint)  {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object[]args = joinPoint.getArgs();
//        System.out.println("------------------------------"+args[0]);
//        System.out.println("------------------------------"+request);
//        System.out.println("------------------------------"+attributes);
    }

    @Around("find()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        System.out.println("环绕通知1");
        System.out.println("Spring版本："+ SpringVersion.getVersion() +" SpringBoot版本："+ SpringBootVersion.getVersion());
        result = point.proceed();
        System.out.println("环绕通知2");
        return result;
    }
}
