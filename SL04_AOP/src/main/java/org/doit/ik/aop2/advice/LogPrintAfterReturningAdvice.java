package org.doit.ik.aop2.advice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component("logPrintAfterReturningAdvice")
@Log4j
public class LogPrintAfterReturningAdvice implements AfterReturningAdvice { // 에러 없이 수행
	
	@Override
	public void afterReturning(
				Object returnValue, // 타겟이 처리한 후에 결과값 
				Method method, // 호출한 메서드
				Object[] args, // 매개변수 
				Object target
				) throws Throwable {
		String methodName = method.getName();
		log.info("<<" +methodName +"()LogPrintAfterReturningAdvice : 호출됨");
		
	} 
	
}
