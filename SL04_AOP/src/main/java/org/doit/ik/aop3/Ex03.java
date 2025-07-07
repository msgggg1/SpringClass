package org.doit.ik.aop3;

import org.doit.ik.aop.Calculator;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex03 {

	public static void main(String[] args) {
		// p209 XML 기반 -> 스프링 AOP (2/3)
		// CalculatorImpl3.java 추가
		// application-context3.xml 추가
		// 1) pom.xml - ㄱ. spring-aop ㄴ.aspectJweaver

		// p.222	AroungAdvice 만드는 방법
		// 2) 공통 기능 구현한 클래스 선언
		// 3) <aop:config> : aop 설정 태그
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:org/doit/ik/aop3/application-context3.xml");
		
		Calculator calc = ctx.getBean("calc3", Calculator.class);
		
		System.out.println(calc.add(10, 2));
		System.out.println("  END. ");
	}

}
