package com.fastinjava.application.base.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@Aspect
public class RequestLogAspect {

    @Pointcut("execution(* com.fastinjava.application.base.web.controller..*(..))")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void before(JoinPoint joinPoint) {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
        log.info("========================log start========================");
//        log.info("ip:                  {}", request.getRemoteAddr());
//        log.info("url:                 {}", request.getRequestURL().toString());
//        log.info("method:              {}", request.getMethod());
//        log.info("class method:        {},{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        /*before*/
        long start = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object result = proceedingJoinPoint.proceed();
        /*after*/
        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setIp(request.getRemoteAddr());
        requestInfo.setUrl(request.getRequestURL().toString());
        requestInfo.setHttpMethod(request.getMethod());
        requestInfo.setClassMethod(String.format("%s.%s", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                proceedingJoinPoint.getSignature().getName()));
        requestInfo.setRequestParams(getRequestParams(proceedingJoinPoint));
        requestInfo.setResult(result);
        requestInfo.setTimeCost(System.currentTimeMillis() - start);
        log.info("Request Info      : {}", requestInfo);

        return result;

    }

    @AfterThrowing(pointcut = "logPointCut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, RuntimeException e) {
        ProceedingJoinPoint proceedingJoinPoint = (ProceedingJoinPoint) joinPoint;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String remoteAddr = request.getRemoteAddr();
        String requestURL = request.getRequestURL().toString();
        String method = request.getMethod();
        String handlerMethod = String.format("%s.%s", proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName());
        Map<String, Object> requestParams = getRequestParams(proceedingJoinPoint);
        RequestErrorInfo requestErrorInfo = new RequestErrorInfo();
        requestErrorInfo.setException(e);
        requestErrorInfo.setIp(remoteAddr);
        requestErrorInfo.setUrl(requestURL);
        requestErrorInfo.setRequestParams(requestParams);
        requestErrorInfo.setClassMethod(handlerMethod);
        requestErrorInfo.setHttpMethod(method);

        log.info("Error Request Info      : {}", requestErrorInfo);

    }

    @After("logPointCut()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("========================log end========================");
    }

    private Map<String, Object> getRequestParams(ProceedingJoinPoint proceedingJoinPoint) {
        Map<String, Object> requestParams = new HashMap<>();
        //参数名
        String[] paramNames =
                ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = proceedingJoinPoint.getArgs();
        for (int i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];
            //如果是文件对象
            if (value instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) value;
                value = file.getOriginalFilename();  //获取文件名
            }
            requestParams.put(paramNames[i], value);
        }
        return requestParams;
    }


    @Data
    public class RequestErrorInfo {
        private String ip;
        private String url;
        private String httpMethod;
        private String classMethod;
        private Object requestParams;
        private RuntimeException exception;
    }

    @Data
    public class RequestInfo {
        private String ip;
        private String url;
        private String httpMethod;
        private String classMethod;
        private Object requestParams;
        private Object result;
        private Long timeCost;
    }
}
