package org.doit.ik.service;

import org.doit.ik.mapper.EmpMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class EmpServiceImpl implements EmpService{

	private EmpMapper empMapper;

	@Override
	public int idCheck(int empno) {
		
		log.info("> EmpServiceImpl.idCheck()");
		return this.empMapper.IdCheck(empno);
	}
	
}
