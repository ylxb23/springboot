package org.zero.boot.global.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

/**
 * for logger
 * @date 2018年1月12日 上午11:21:24
 * @author zero
 */
@Aspect
@Component
public class Aspect4Logger {
	private final Logger logger = LoggerFactory.getLogger(Aspect4Logger.class);
	
	/*
	@Pointcut("execution(* org.zero.boot.domain.service.impl.*.*(..))")
	public void anyMethod() {}
	
	
	@Before("execution(* org.zero.boot.domain.service.impl.*.*(..))")
	public void doBefore(JoinPoint point) {
		logger.info("Before execute[{}] with args[{}] ", point.getTarget().getClass(), JSONObject.toJSONString(point.getArgs()));
	}
	
	@AfterReturning(value="anyMethod()", returning="result")
	public void doAfter(JoinPoint point, Object result) {
		logger.info("After execute[{}] with args[{}] return [{}]", point.getTarget().getClass(), JSONObject.toJSONString(point.getArgs()), JSONObject.toJSONString(result));
	}
	*/
	
	@Around("execution(* org.zero.boot.domain.service.impl.*.*(..))")
	public Object doAround(ProceedingJoinPoint point) throws Throwable {
		long begin = System.currentTimeMillis();
		Object result = point.proceed();
		long end = System.currentTimeMillis();
		logger.info("Around execute at[{}.{}], with args[{}], cost[{}]ms, return[{}].", point.getTarget().getClass(), point.getSignature().getName(), JSONObject.toJSONString(point.getArgs()), (end - begin), JSONObject.toJSONString(result));
		return result;
	}
	
}
