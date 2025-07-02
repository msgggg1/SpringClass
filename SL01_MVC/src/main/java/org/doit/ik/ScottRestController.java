package org.doit.ik;

import org.doit.ik.domain.DeptDTO;
import org.doit.ik.mapper.scott.DeptMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;

@RestController //응답 데이터 알아서 JSON으로 돌려줌, 자동으로 변환해서 객체로 받음
public class ScottRestController {

	@Setter(onMethod=@__({@Autowired})) // 롬복으로 주입 어노테이션
	private DeptMapper deptMapper;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	//post, put, delete, 
	@PostMapping("/scott/dept/new")
	public ResponseEntity<String> insertDept(@RequestBody DeptDTO dto) {

		logger.info("> ScottController.insertDept() 컨트롤러 메서드 호출됨...");
		
		int rowCount = this.deptMapper.insertDept(dto);

		return rowCount==1
	            ? new ResponseEntity<>("SUCCESS", HttpStatus.OK)
	                  : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) ;		
	}

	// url: '/scott/dept/50' + delete
	@DeleteMapping("/scott/dept/{deptno}")
	public ResponseEntity<String> deleteDept( @PathVariable("deptno") int deptno) {
		logger.info("> ScottController.deleteDept() 컨트롤러 메서드 호출됨...");
		int rowCount = this.deptMapper.deleteDept(deptno);

		return rowCount==1
	            ? new ResponseEntity<>("SUCCESS", HttpStatus.OK)
	                  : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) ;		
	}
}
