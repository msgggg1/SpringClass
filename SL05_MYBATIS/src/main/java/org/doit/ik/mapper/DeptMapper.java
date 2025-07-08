package org.doit.ik.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.DeptDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public interface DeptMapper {

	ArrayList<DeptDTO> selectDept();
	
	// 부서 추가 추상 메서드
	int insertDept(DeptDTO dto);
	
	// 부서 삭제 추상 메서드
	int deleteDept(@Param("deptno") int deptno); //Integer.deptno로 인식 -> @Param : 기본자료형임을 알려줌. 
												// 객체가 아닌경우 @Param 줘서 인식할 수 있도록 명시
}
