package org.doit.ik.aop;

public class CalculatorImpl implements Calculator {

	@Override
	public int add(int x, int y) {
		long start = System.nanoTime(); // 1/10^9 , 공통기능
		int result = x + y ; // 핵심 관심 사항
		long end = System.nanoTime(); // 공통기능
		System.out.printf("> 덧셈 처리 시간 : %d ns\n", (end-start)); // 공통기능
		return result;
	}

	@Override
	public int sub(int x, int y) {
		long start = System.nanoTime(); // 공통기능
		int result = x - y ; // 핵심 관심 사항
		long end = System.nanoTime(); // 공통기능
		System.out.printf("> 덧셈 처리 시간 : %d ns\n", (end-start)); // 공통기능
		return result;
	}

	@Override
	public int mul(int x, int y) {
		long start = System.nanoTime(); // 공통기능
		int result = x * y ; // 핵심 관심 사항
		long end = System.nanoTime(); // 공통기능
		System.out.printf("> 덧셈 처리 시간 : %d ns\n", (end-start)); // 공통기능
		return result;
	}

	@Override
	public int div(int x, int y) {
		long start = System.nanoTime(); // 공통기능
		int result = x / y ; // 핵심 관심 사항
		long end = System.nanoTime(); // 공통기능
		System.out.printf("> 덧셈 처리 시간 : %d ns\n", (end-start)); // 공통기능
		return result;
	}

}
