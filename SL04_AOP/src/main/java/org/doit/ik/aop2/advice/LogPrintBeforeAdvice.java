package org.doit.ik.aop2.advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component("logPrintBeforeAdvice")
@Log4j
public class LogPrintBeforeAdvice implements MethodBeforeAdvice{

	@Override										
	public void before(
						Method method,// 		add()
						Object[] args,// 매개변수 int x, int y
						Object target // 핵심관심사항 구현한 실제 객체
						
						) throws Throwable {
		
		String methodName = method.getName(); //호출하는 메서드 이름
		log.info(" > " + methodName +"() : LogPrintBeforeAdvice 호출됨");
		
		
	}
	
}
