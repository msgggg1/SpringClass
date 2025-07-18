package org.doit.ik.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.DeptVO;

public interface DeptMapper {	
	
	List<DeptVO> getDeptList();
	int insertDept(DeptVO deptVO);
	
	DeptVO getDept(@Param("deptno") int deptno) ; 
	int updateDept(DeptVO deptVO) ; 
	int deleteDept(@Param("deptno") int deptno) ; 
	
}
