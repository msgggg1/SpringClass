package org.doit.ik;

import org.doit.ik.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class EmpController {
	
	@Autowired
	private EmpService empService;
	
	//[1] /emp/empno 요청 + GET - 컨트롤러 매서드
	@GetMapping("/emp/empno")
	public String checkEmpno(@RequestParam("empno") int empno) {
		boolean isAvailable = this.empService.isEmpnoAvailable(empno);
		return isAvailable ? "available" : "duplicate";
	};

	//[2] /empnoCheck/7369 요청 + GET - RESTfull 컨트롤러 메서드
	@GetMapping("/empnoCheck/{empno}")
	public String checkEmpno2(@PathVariable("empno") int empno) {
		boolean isAvailable = this.empService.isEmpnoAvailable(empno);
		return isAvailable ? "available" : "duplicate";
	};
	
}
