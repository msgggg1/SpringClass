package org.doit.ik.di.test;

import org.doit.ik.di.Record;
import org.doit.ik.di.RecordImpl;
import org.doit.ik.di.RecordViewImpl;

public class ex01 {

	public static void main(String[] args) {
		// p.40 3.5 스프링을 사용하지 안고 객체 사용/조립하기
		//				- 성적정보 입력받아 출력
		/*
		 * 1. Record.java		  인터페이스
		 * 2. RecordImpl.java	  구현클래스
		 * 3. RecordView.java	  인터페이스
		 * 4. RecordViewImpl.java 구현클래스
		 * */
		
		RecordImpl record = new RecordImpl();			// A
		
		// [1] 주입 방법 : 생성자 주입, 
		// RecordViewImpl rvi = new RecordViewImpl(record);	// B에게 A 주입
		// [2] 주입 방법 : setter 
		RecordViewImpl rvi = new RecordViewImpl(); // B에게 A 주입 rvi.setRecord(record);

		
		rvi.input(); 	// 성적 입력
		rvi.output();	// 성적 출력
		
		System.out.println("END. ");

	}

}
