package com.way.apiinterface.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * 校验角色权限
 *
 * @author Way
 */
@Aspect
@Component
@Slf4j
@Order(10)
public class CheckAspect {
    @Pointcut("execution(* com.way.apiinterface.controller.*.*(..))")
    public void checkReqSource() {
    }

    @Around("checkReqSource()")
    public Object doCheckReqSource(ProceedingJoinPoint pjp) throws Throwable {
        log.info("check whether the request is coming  from gateway. ");
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        Cookie[] cookies = request.getCookies();

        log.info("req cookies: {}", Arrays.toString(cookies));
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("fromWhere") && cookie.getValue().equals("api-gateway")) {
                    return pjp.proceed();
                }
            }
        }
        HttpServletResponse response = servletRequestAttributes.getResponse();
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().close();
        return null;
    }
}
