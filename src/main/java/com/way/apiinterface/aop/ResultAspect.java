package com.way.apiinterface.aop;

import cn.hutool.json.JSONUtil;
import com.way.dubbointerface.common.BaseResponse;
import com.way.dubbointerface.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 封装结果
 */
@Slf4j
@Aspect
@Component
public class ResultAspect {
    @Pointcut("execution(String com.way.apiinterface.controller.*.*(..))")
    public void wrapResult() {
    }

    @Around("wrapResult()")
    public Object doWrapResult(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("处理 {} 返回结果",joinPoint.getSignature().getName());
        //执行方法，获取返回值
        Object obj = joinPoint.proceed();
        //处理方法返回值
        BaseResponse<Object> response = ResultUtils.success(obj);

        return JSONUtil.toJsonStr(response);
    }
}
