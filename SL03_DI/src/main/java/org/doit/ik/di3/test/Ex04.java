package org.doit.ik.di3.test;

import org.doit.ik.di3.RecordViewImpl3;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex04 {

	public static void main(String[] args) {
		// p.103 애노테이션을 이용한 객체 간 의존 자동 연결
		
		// p59 ApplicationContext 종류 및 설명
		String [] resourceLocations = {"classpath:org/doit/ik/di3/application-context.xml"};
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		
		RecordViewImpl3 rvi = ctx.getBean("rvi", RecordViewImpl3.class);
		rvi.input(); 	// 성적 입력
		rvi.output();	// 성적 출력
 
		System.out.println("END. ");
		
	}

}
