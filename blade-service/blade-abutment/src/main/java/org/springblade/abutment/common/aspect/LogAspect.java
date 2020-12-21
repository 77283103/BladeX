package org.springblade.abutment.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogAspect {

    //用来记录请求进入的时间，防止多线程时出错，这里用了ThreadLocal
    ThreadLocal<Long> startTime = new ThreadLocal<>();
    /**
     * 定义切入点，controller下面的所有类的所有公有方法，这里需要更改成自己项目的
     */
    @Pointcut("@annotation(org.springblade.abutment.common.annotation.AutoLog)")
    public void requestLog(){};

    /**
     * 方法之前执行，日志打印请求信息
     * @param joinPoint joinPoint
     */
    @Before("requestLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        //打印当前的请求路径
        log.info("RequestMapping:[{}]",request.getRequestURI());

        //打印请求参数，如果需要打印其他的信息可以到request中去拿
        log.info("RequestParam:{}", Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 方法返回之前执行，打印才返回值以及方法消耗时间
     * @param response 返回值
     */
    @AfterReturning(returning = "response",pointcut = "requestLog()")
    public void doAfterRunning(Object response) {
        //打印返回值信息
        log.info("Response:[{}]",response );
        //打印请求耗时
        log.info("Request spend times : [{}ms]",System.currentTimeMillis()-startTime.get());
    }
}
