package org.doit.ik.aop2.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.log4j.Log4j;

@Component("logPrintAroundAdvice")
@Log4j
public class LogPrintAroundAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		
		StopWatch sw = new StopWatch();
		sw.start();
		// 호출한 함수 이름 얻어오기
		String methodName = method.getMethod().getName();
		log.info("> " + methodName +"() start.");
		
		
		// 실제 핵심기능을 하는 타켓객체 메서드를 중간에 호출
		Object result = method.proceed(); // target
		
		log.info("> " + methodName +"() stop.");
		sw.stop();
		log.info("> " + methodName +"() 처리 시간 : " + sw.getTotalTimeMillis() + "ms");
		
		return result;
	}
	
}
