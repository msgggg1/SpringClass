package org.doit.ik;


import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.doit.ik.domain.DeptDTO;
import org.doit.ik.domain.EmpDTO;
import org.doit.ik.mapper.scott.DeptMapper;
import org.doit.ik.mapper.scott.EmpMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ScottController {
	
	private static final Logger logger = LoggerFactory.getLogger(ScottController.class);

	@Autowired
	private DeptMapper deptMapper;
	@Autowired
	private EmpMapper empMapper;
	
	@GetMapping(value="/scott/dept")
	public String dept(HttpServletRequest request ) {
		
		ArrayList<DeptDTO> list = this.deptMapper.selectDept();
		request.setAttribute("list", list);
		
		return "scott/dept";

	}

	@PostMapping(value="/scott/emp")
	public String emp(HttpServletRequest request , @RequestParam("deptno") int [] deptnoArr ) {
		
		ArrayList<EmpDTO> list = this.empMapper.selectEmp(deptnoArr);
		request.setAttribute("list", list);
		
		return "scott/emp";
		
	}
	
}
