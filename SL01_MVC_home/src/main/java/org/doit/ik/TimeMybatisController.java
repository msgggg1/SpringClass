package org.doit.ik;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.doit.ik.mapper.TimeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class TimeMybatisController {
	
	private static final Logger logger = LoggerFactory.getLogger(TimeMybatisController.class);
	
	// DI
	@Autowired
	private TimeMapper timeMapper;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/time", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("> TimeMybatisController.time() 컨트롤러 메서드 호출됨");
		
		String currentTime = this.timeMapper.getTime();		
		model.addAttribute("currentTime", currentTime );
		String nextTime = this.timeMapper.getNextTime();		
		model.addAttribute("nextTime", nextTime );
		
		return "time";
	}
	
}
