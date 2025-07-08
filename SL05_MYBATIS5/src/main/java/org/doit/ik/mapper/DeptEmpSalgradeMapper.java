package org.doit.ik.mapper;

import java.util.List;

import org.doit.ik.domain.DeptEmpSalgradeDTO;
import org.doit.ik.domain.EmpDTO;

public interface DeptEmpSalgradeMapper {
	
	
	// List<DeptEmpSalgradeDTO> getDeptEmpInfo();
	
	// 1. 모든 부서 정보를 조회 select
	List<DeptEmpSalgradeDTO> getDept();
	// 2. 해당 부서원 정보를 조회 select
	List<EmpDTO> getEmpOfDept(int deptno);
	
}
