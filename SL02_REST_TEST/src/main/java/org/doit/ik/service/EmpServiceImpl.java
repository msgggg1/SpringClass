package org.doit.ik.service;

import org.doit.ik.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class EmpServiceImpl implements EmpService {

	//@Autowired
	//@Setter
	private EmpMapper empMapper;
	
	// EmpDTO가 null인 경우에는 empno 는 사용가능: true 리턴
	@Override
	public boolean isEmpnoAvailable(int empno) {
	
		return this.empMapper.selectUserByEmpno(empno) == null;
	}

}
