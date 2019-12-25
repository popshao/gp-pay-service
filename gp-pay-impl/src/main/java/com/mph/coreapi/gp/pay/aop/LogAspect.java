package com.mph.coreapi.gp.pay.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: haiquan.meng
 * @Email: haiquan.meng@rograndec.com
 * @CreateDate: 2018/8/17
 * @Version: 1.0
 */
@Aspect
@Component
public class LogAspect {
    Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Around("execution(public * com.mph.coreapi.gp.pay.service..*.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        Object[] args = joinPoint.getArgs();
        logger.info("Invoke service method " + className + "." + method +
                " parameter is: " + JSON.toJSONString(args));
        Object result = joinPoint.proceed();
        /*logger.info("Invoke service method " + className + "." + method +
                " result is: " + JSON.toJSONString(result));*/
        return result;
    }
}
