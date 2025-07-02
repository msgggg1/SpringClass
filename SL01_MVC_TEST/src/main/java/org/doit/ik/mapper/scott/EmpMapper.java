package org.doit.ik.mapper.scott;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.DeptDTO;
import org.doit.ik.domain.EmpDTO;

public interface EmpMapper {
	
	ArrayList<EmpDTO> selectEmp();
	ArrayList<EmpDTO> selectEmp(int [] deptnoArr);
	ArrayList<EmpDTO> selectEmpDept( @Param("deptno") int deptno);
	
}
