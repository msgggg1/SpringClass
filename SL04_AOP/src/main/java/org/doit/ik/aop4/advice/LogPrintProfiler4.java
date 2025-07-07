package org.doit.ik.aop4.advice;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.log4j.Log4j;

@Aspect		// <aop:aspect id="traceAspect" ref="logPrintProfiler4">
@Component
@Log4j
public class LogPrintProfiler4 {
	//<aop:pointcut expression="execution(* org.doit.ik.aop..*.*(*,*))" id="calcPointcut"/>
	@Pointcut("execution(* org.doit.ik.aop..*.*(..))")
	private void calcPointcut() {
		
	}

	@Before("calcPointcut()")
	public void before(JoinPoint jointpoint) throws Throwable {

		String methodName = jointpoint.getSignature().getName(); //호출하는 메서드 이름
		log.info(" > " + methodName +"() : LogPrintProfiler4.before() 호출됨");

	}
	// p.218 after returning advice 하나의 클래스 안에 각각 선언	
	// 주의할 점 : returning="이름"은 메서드 파라미터 이름과 정확히 일치해야 함. 
	@AfterReturning(pointcut = "calcPointcut()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) throws Throwable {
		String methodName = joinPoint.getSignature().getName();
		log.info("<<" +methodName +"() :LogPrintProfiler4.afterReturning() 호출됨" + result);

	} 

	//<aop:around method="trace" pointcut-ref="calcPointcut" />
	@Around("calcPointcut()")
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {

		StopWatch sw = new StopWatch();
		sw.start();

		// == method()
		String methodName = joinPoint.getSignature().getName(); 

		log.info("> " + methodName +"() start.");

		// 실제 핵심기능을 하는 타켓객체 메서드를 중간에 호출
		Object result = joinPoint.proceed(); // target

		log.info("> " + methodName +"() stop.");
		sw.stop();
		log.info("> " + methodName +"() 처리 시간 : " + sw.getTotalTimeMillis() + "ms");

		return result;
	}
}
