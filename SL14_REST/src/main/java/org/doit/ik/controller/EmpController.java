package org.doit.ik.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.doit.ik.domain.EmpVO;
import org.doit.ik.domain.SampleVO;
import org.doit.ik.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@RestController 
@RequestMapping("/scott") 
@Log4j
public class EmpController { 

	@Autowired
	private EmpMapper empMapper;

	/*
	@GetMapping(value = "/employee/{empno}",
            produces = {
                  MediaType.APPLICATION_JSON_UTF8_VALUE,
                  MediaType.APPLICATION_XML_VALUE
               })
   public EmpVO selectByEmpno(@PathVariable("empno") int empno) {
      return this.empMapper.selectByEmpno(empno);
   }
	 */

	//[2] 응답데이터 + 상태 메시지 : 더 좋은 코딩
	@GetMapping(value = "/employee/{empno}",
			produces = {
					MediaType.APPLICATION_JSON_UTF8_VALUE,
					MediaType.APPLICATION_XML_VALUE
	})
	public ResponseEntity<EmpVO> selectByEmpno(@PathVariable("empno") int empno) {
		EmpVO empVO = this.empMapper.selectByEmpno(empno);
		if (empVO == null) {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
					.body(empVO);
		}else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(empVO);
		}

	}


	// 아이디 중복체크 메서드
	@GetMapping(value = "/idCheck/{empno}",
			produces = {
					MediaType.APPLICATION_JSON_UTF8_VALUE
	})
	public Integer idCheck(@PathVariable("empno") String empno) {
		return this.empMapper.idCheck(empno);

	}

	@GetMapping("/emplist")
	public List<EmpVO> getEmpList(){
		return this.empMapper.selectAll();
	}

}
