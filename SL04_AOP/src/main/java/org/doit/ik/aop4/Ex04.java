package org.doit.ik.aop4;

import org.doit.ik.aop.Calculator;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex04 {

	public static void main(String[] args) {
		// p226 @aspect 애노테이션 기반 -> 스프링 AOP (3/3) ***
				
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:org/doit/ik/aop4/application-context4.xml");
		
		Calculator calc = ctx.getBean("calc4", Calculator.class);
		
		System.out.println(calc.add(1, 2));
		System.out.println("  END. ");
		
	}

}
