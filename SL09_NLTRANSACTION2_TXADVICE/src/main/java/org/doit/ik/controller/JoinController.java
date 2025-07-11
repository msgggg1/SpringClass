package org.doit.ik.controller;

import java.beans.PropertyEditorSupport;

import org.doit.ik.domain.MemberVO;
import org.doit.ik.persistence.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/joinus/*")
public class JoinController {
	
	@Autowired
	private MemberDao memberDao;
	
	// 회원가입  /joinus/join.htm ->	/joinus/join.jsp
	@GetMapping("/join.htm")
	public String join() throws Exception {
		return "join.jsp";
	}
	
	
	/*
	@PostMapping("/join.htm")
	public String join(MemberVO member
						, @RequestParam("year") String year
						, @RequestParam("month") String month
						, @RequestParam("day") String day
						, @RequestParam(value="habit", required=false) String [] habits
						) throws Exception {
		// 1. 
		String birth = String.format("%s-%s-%s", year, month, day);
		member.setBirth(birth);
		
		//2. 
		String combinedHabit = String.join(",", habits);
		member.setHabit(combinedHabit);
		
		this.memberDao.insert(member);
	    
		return "redirect:../index.htm"; 
	}
	*/
	
	// [2] p334 요청 파라미터의 값 변환처리
	   @InitBinder
	   public void initBinder(WebDataBinder binder) { 
	      
	       binder.registerCustomEditor(String.class, "habit", new PropertyEditorSupport() {
	           @Override
	           public void setAsText(String text) {
	               setValue(text);
	           }

	           @Override
	           public void setValue(Object value) {
	               if (value instanceof String[]) {
	                   String joined = String.join(",", (String[]) value);
	                   super.setValue(joined);
	               } else {
	                   super.setValue(value);
	               }
	           }
	       });
	   }
	   
	   @PostMapping("/join.htm")
	   public String join( 
	         MemberVO member
	         , @RequestParam("year") String year
	         , @RequestParam("month") String month
	         , @RequestParam("day") String day 
	         ) throws Exception{
	      // 1.
	      String birth = String.format("%s-%s-%s", year, month, day);
	      member.setBirth(birth);
	       
	      this.memberDao.insert(member);
	      return "redirect:../index.htm";   //   
	   }
	
	/* [나]
	@PostMapping("/join.htm")
	public String join(MemberVO member) {
		int rowcount = 0;
		try {
			rowcount = memberDao.insert(member);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		if (rowcount == 1) {
			return "redirect:/index.htm?success=";
		} else {
			return "redirect:join.htm?fail=";
		}		
	}
	*/
	
	// 로그인   /joinus/login.htm ->  /joinus/login.jsp
	@GetMapping("/login.htm")
	public String login() throws Exception {
		return "login.jsp";
	}
	
}
