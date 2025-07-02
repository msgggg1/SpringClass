package org.doit.ik;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.doit.ik.domain.DeptDTO;
import org.doit.ik.domain.EmpDTO;
import org.doit.ik.mapper.scott.DeptMapper;
import org.doit.ik.mapper.scott.EmpMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Setter;

@Controller
public class ScottController {
	
	// DI
	// @Autowired
	@Setter(onMethod=@__({@Autowired})) // 롬복으로 주입 어노테이션
	private DeptMapper deptMapper;
	
	@Autowired
	private EmpMapper empMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// 		/scott/dept 요청 -> 컨트롤러 메서드 선언
	// @RequestMapping(value="/scott/dept", method = RequestMethod.GET) // 아래 코딩과 100% 동일
	@GetMapping(value="/scott/dept")
	public String dept(HttpServletRequest request) {
		
		logger.info("> ScottController.dept() 컨트롤러 메서드 호출됨...");
		
		ArrayList<DeptDTO> list = this.deptMapper.selectDept();
		request.setAttribute("list", list);
		
		return "scott/dept";		
	}

	// 부서원 검색하는 컨트롤러 메서드 선언
	//@RequestMapping(value="/scott/emp", method = RequestMethod.POST) // 아래 코딩과 100% 동일
	@PostMapping(value="/scott/emp")
	public String emp(HttpServletRequest request , @RequestParam("deptno") int [] deptnoArr) {
		
		logger.info("> ScottController.emp() 컨트롤러 메서드 호출됨...");
		
		// [1] JSP - MVC 패턴
		/*
		// deptno=10&deptno=20
		int [] deptnoArr = null;
		String [] pDeptnoArr = request.getParameterValues("deptno");
		deptnoArr = new int [pDeptnoArr.length];
		for (int i = 0; i < pDeptnoArr.length; i++) {
			deptnoArr[i] = Integer.parseInt( pDeptnoArr[i] );
		}
		*/
		
		
		//ArrayList<EmpDTO> list = this.empMapper.selectEmp();
		ArrayList<EmpDTO> list = this.empMapper.selectEmp(deptnoArr);
		request.setAttribute("list", list);
		
		return "/scott/emp";		
	}
	
	
	/* 내 코딩
	@Autowired
	private EmpMapper empMapper;
	
	@PostMapping(value="/scott/emp")
	public String emp(HttpServletRequest request) {
		
		logger.info("> ScottController.emp() 컨트롤러 메서드 호출됨...");
		
		ArrayList<EmpDTO> list = this.empMapper.selectEmp();
		request.setAttribute("list", list);
		
		return "scott/emp";		
	}
	*/
	
	
	
	
}
