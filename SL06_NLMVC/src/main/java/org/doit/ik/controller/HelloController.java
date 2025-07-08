package org.doit.ik.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// p.264
@Controller
public class HelloController {

	@RequestMapping("/hello.do")
	public String hello (Model model) {
		model.addAttribute("greeting", "안녕하세요");
		return "hello";
	}
}