package org.doit.ik.controller;

import java.beans.PropertyEditorSupport;

import org.doit.ik.domain.MemberVO;
import org.doit.ik.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	private MemberMapper memberDao;
	
	// 회원가입  /joinus/join.htm ->	/joinus/join.jsp
	@GetMapping("/join.htm")
	public String join() throws Exception {
		return "joinus.join";
	}
	
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
	
	
	// 로그인   /joinus/login.htm ->  /joinus/login.jsp
	// 로그인   /joinus/login.htm?error ->  /joinus/login.jsp
	// 로그인   /joinus/login.htm?logout ->  /joinus/login.jsp
	@GetMapping("/login.htm")
	public String login(String error, String logout, Model model) throws Exception {
		log.info(">error :" + error);
		log.info(">logout :" + logout);
		
		if (error != null) {
			model.addAttribute("error", "로그인 실패로~~;");
		}
		if (logout != null) {
			model.addAttribute("logout", "로그아웃 실패로~~;");
		}
		return "joinus.login";
	}
	
}
