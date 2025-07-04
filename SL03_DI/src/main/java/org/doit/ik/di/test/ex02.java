package org.doit.ik.di.test;

import org.doit.ik.di.RecordImpl;
import org.doit.ik.di.RecordViewImpl;
import org.springframework.context.support.GenericXmlApplicationContext;

public class ex02 {

	public static void main(String[] args) {
		// p.42 스프링으로 객체 조립하기: 스프링 설정 만들기
		//			(성적정보 입력받아 출력하기)    

		// application-context.xml : 스프링 빈(객체) 생성, 조립시 사용할 설정 파일.

		/* application-context.xml에서 생성함.
		 * RecordImpl record = new RecordImpl(); RecordViewImpl rvi = new
		 * RecordViewImpl();
		 */
		/*
		 * WebApplicationContext 5종류중 하나. 그 안에 있는 빈 객체 올라감
		 * */
		
		// p59 ApplicationContext 종류 및 설명
		String [] resourceLocations = {"classpath:org/doit/ik/di/application-context.xml"};
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		
		//RecordViewImpl rvi = (RecordViewImpl) ctx.getBean("rvi");
		RecordViewImpl rvi = ctx.getBean("rvi", RecordViewImpl.class);
		rvi.input(); 	// 성적 입력
		rvi.output();	// 성적 출력

		System.out.println("END. ");
	}

}
