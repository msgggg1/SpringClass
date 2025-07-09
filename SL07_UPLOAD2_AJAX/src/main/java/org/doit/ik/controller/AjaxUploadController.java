package org.doit.ik.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/ajax/*")
public class AjaxUploadController {
	//	/ajax/upload
	@GetMapping("/ajax/upload")
	public void ajaxupload() {

	}
}
