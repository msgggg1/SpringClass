package org.doit.ik;

import java.util.List;
import java.util.Locale;

import org.doit.ik.domain.DeptEmpSalgradeDTO;
import org.doit.ik.domain.EmpDTO;
import org.doit.ik.mapper.DeptEmpSalgradeMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
public class DeptEmpSalgradeController {
	
	//@Autowired
	private DeptEmpSalgradeMapper deptEmpSalgradeMapper;
	
	@GetMapping(value = "/dept/emp")
	public void getDeptEmpSalgrade(Locale locale, Model model) {
		log.info("> DeptEmpSalgradeController.getDeptEmpSalgrade() 호출");
		
		List<DeptEmpSalgradeDTO> desList = this.deptEmpSalgradeMapper.getDept();
		
		// foreach 문
		for (DeptEmpSalgradeDTO dto : desList) {
			List<EmpDTO> empList = this.deptEmpSalgradeMapper.getEmpOfDept(dto.getDeptno());
			dto.setEmpList(empList);
		} // for
		
		model.addAttribute("desList", desList);
	}
	
}
