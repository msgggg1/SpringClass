package org.doit.ik.mapper;

import java.util.List;

import org.doit.ik.domain.EmpVO;

public interface EmpMapper {	
	
	EmpVO selectByEmpno(int empno);
	
	Integer idCheck(String empno); // 아이디 중복체크  / 래퍼클래스 쓰는게 좋음
	
	List<EmpVO> selectAll();
	
}
