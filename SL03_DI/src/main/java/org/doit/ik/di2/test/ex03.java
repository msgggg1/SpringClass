package org.doit.ik.di2.test;



import org.doit.ik.di.RecordViewImpl;
import org.doit.ik.di2.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ex03 {

	public static void main(String[] args) {
		// p85 스프링을 사용해서 객체 조립/사용하기(자바 코드)
		// 			성적정보 입력받아서 출력
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		
		RecordViewImpl rvi = ctx.getBean("rvi", RecordViewImpl.class);
		rvi.input(); 	// 성적 입력
		rvi.output();	// 성적 출력

		System.out.println("END. ");
	}

}
