package org.doit.ik.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/common/*")
public class CommonController {
	
	@GetMapping("/accessError.htm")
	public String accessDenied(Model model, Authentication auth) {
		log.info("> /common/accessError.htm - GET");
		model.addAttribute("msg", "접근 금지됨");
		
		// defaultviewResolver에 의해 
		//  /WEB-INF/views/common/accessError.jsp 요청
		return "/common/accessError";
	}
}
