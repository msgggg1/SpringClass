package org.doit.ik.mapper;

import org.doit.ik.domain.EmpDTO;

public interface EmpMapper {
	
	EmpDTO selectUserByEmpno(int empno);
	
}
