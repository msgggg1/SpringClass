package org.doit.ik.aop;

public class Ex01 {
	
	public static void main(String[] args) {
		/*
		 * p.204 [스프링 AOP]
		 * 
		 * 예) 게시판 글쓰기
		 * 	  공통기능 + 핵심 비즈니스 로직 기능
		 * 	   인증			글쓰기
		 *    게시판 글수정/삭제
		 * 	  공통기능 + 핵심 비즈니스 로직 기능
		 * 	   인증			수정,삭제
		 * 	   권한
		 * 	  공통관심사항(있는 경우)		핵심관심사항
		 * 	cross-cutting concern	core concern
		 * 
		 * 실습
		 * 	1) org.doit.ik.aop 패키지 + 스프링 AOP X (공통기능 코딩으로 다 구현)
		 * 	   ㄴ Calculator 인터페이스
		 * 	   ㄴ CalculatorImpl 클래스
		 * 	   ㄴ Ex01. java 테스트
		 * 		
		 */
		
		Calculator calc = new CalculatorImpl();
		
		System.out.println(calc.add(4, 2));
		System.out.println(calc.sub(4, 2));
		System.out.println(calc.mul(4, 2));
		System.out.println(calc.div(4, 2));
		
		System.out.println("  END ");

	}
}
