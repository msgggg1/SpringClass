package org.doit.ik.mapper.scott;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.DeptDTO;

public interface DeptMapper {

	ArrayList<DeptDTO> selectDept();
	
	// 부서 추가 추상 메서드
	int insertDept(DeptDTO dto);
	
	// 부서 삭제 추상 메서드
	int deleteDept(@Param("deptno") int deptno);
}
