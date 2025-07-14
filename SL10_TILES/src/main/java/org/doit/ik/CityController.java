package org.doit.ik;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/city/*")
public class CityController {
	
	@GetMapping("/london")
	public String london() {
		log.info("> CityController.london() 호출");
		return "city/london.tiles"; // 상관없음
	}
	@GetMapping("/paris")
	public String paris() {
		log.info("> CityController.paris() 호출");
		return "city/paris.tiles"; // 상관없음
	}
	@GetMapping("/seoul")
	public String seoul() {
		log.info("> CityController.seoul() 호출");
		return "city/seoul.tiles"; // 상관없음
	}
	
	
}
