package org.doit.ik.aop3.advice;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
public class LogPrintProfiler3 {
	// before advice	// 매개변수 필요하면 joinPoint(메서드 정보)
	public void before(JoinPoint jointpoint) throws Throwable {

		String methodName = jointpoint.getSignature().getName(); //호출하는 메서드 이름
		log.info(" > " + methodName +"() : LogPrintProfiler3.before() 호출됨");

	}
	// p.218 after returning advice 하나의 클래스 안에 각각 선언	
		public void afterReturning(JoinPoint joinPoint, Object result) throws Throwable {
			String methodName = joinPoint.getSignature().getName();
			log.info("<<" +methodName +"() :LogPrintProfiler3.afterReturning() 호출됨" + result);
			
		} 

	//p.222 around advice  줄거면 이거, 아니면 없게
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
