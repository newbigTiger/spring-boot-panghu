package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Aspect
@Component
public class DemoAop {

    @Pointcut("execution(* com.example.demo.controller.DemoController.*(..))")
    public void getPointcut(){
    }

    @Around("getPointcut()")
    public Object getAround(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getResponse();
        request.getHeader("token");
        response.setHeader("Content-type","application/json;charset:UTF-8");
        response.getWriter().write("json");
        Object[] args = joinPoint.getArgs();
        Object proceed = joinPoint.proceed(args);

        return proceed;
    }

}
