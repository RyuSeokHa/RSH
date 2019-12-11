package com.team404.util.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAdvice {
	//log4j.xml에 선언
	//<logger name="com.team404.util.aop">
	//	<level value="info" />
	//</logger>
	//로그를 찍을수 있게 처리하는 기능
	private static final Logger log = LoggerFactory.getLogger(LogAdvice.class);
		
	@Around("execution(* com.team404.*.service.*ServiceImpl.*(..))")
	public Object aroundLog(ProceedingJoinPoint jp) {
		
		log.info("적용클래스:" +  jp.getTarget());
		log.info("적용메서드:" +  jp.getSignature().toString() );
		log.info("적용파라미터:" + Arrays.toString(jp.getArgs() ));
		
		long start = System.currentTimeMillis();
		Object result = null;
		
		try {
			 result = jp.proceed();//이 메서드를 만나야 서비스의 메서드가 실행됨
		} catch (Throwable e) {
			e.printStackTrace();
		} 
		
		long end = System.currentTimeMillis();
		log.info("메서드수행시간:" + (end-start)*0.001 +"초");

		return result;
	}
	
	
	
}
