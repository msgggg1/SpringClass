package org.doit.ik.controller;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;
import org.doit.ik.domain.PageDTO;
import org.doit.ik.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@AllArgsConstructor
@Controller
@Log4j
@RequestMapping("/board/*")
public class BoardController {
	
	private BoardService boardService;	
	
	/* [1] 페이징 처리 X 컨트롤러 메서드 
	@GetMapping(value = "/list")
	public void list(Model model) {
		log.info(">BoardController.list()");
		model.addAttribute("list", this.boardService.getList());
	}
	*/
	
	// [2] 페이징 처리 O 컨트롤러 메서드
	// http://localhost/board/list?pageNum=2&amount=10
	@GetMapping(value = "/list")
	public void list(Model model, Criteria criteria) { // 자동으로 커맨드 객체 생성 / 안넘어오면 default 생성자에 의해 기본값으로 만들어짐
		log.info(">BoardController.list()");
		model.addAttribute("list", this.boardService.getListWithPaging(criteria));
		// list.jsp 포워딩 : 페이징 블럭 출력
		int total = this.boardService.getTotal(criteria);
		model.addAttribute("pageMaker", new PageDTO(criteria, total));  
	}	

	@GetMapping(value = "/register")
	public void register() {
		log.info(">BoardController.register() - GET ");
	}

	@PostMapping(value = "/register")
	public String register(BoardVO boardVO, RedirectAttributes rttr ) { // name 속성만 같으면 자동으로 받아짐. r.gp~ x
		log.info(">BoardController.register() - POST");
		this.boardService.register(boardVO);
		// rttr.addAttribute("result", 5); 특징 이해 // 쿼리스트링 param.result
		//rttr.addFlashAttribute("result", 9); // 세션 1회성
		//return "redirect:/board/list?result=4";
		rttr.addFlashAttribute("result", boardVO.getBno()); // 세션 1회성
		
		return "redirect:/board/list";
		//return "redirect:/board/list?error"; // 기본 포워딩, 리다이렉트 원할 시 이렇게
		
	}
	
	// [1] "/board/modify?bno=${board.bno}" + GET
	// [1] "/board/get?bno=${board.bno}" + GET
	@GetMapping(value = {"/get" , "/modify"})
	public void get(@RequestParam("bno") Long bno, Model model , @ModelAttribute("criteria") Criteria criteria) {
		log.info(">BoardController.get() - GET");
		BoardVO boardVO = this.boardService.get(bno);
		
		model.addAttribute("boardVO", boardVO);
		//model.addAttribute("criteria", criteria); // @ModelAttribute가 한번에 해줌
		// return "/board/get"
	}
	
	// [1] "/board/modify?bno=${board.bno}" + GET

	/*
		//    /board/get/7.jsp
		// [2] "/board/get/{board.bno}" + GET
		@GetMapping(value = "/get/{bno}")
		public String get(@PathVariable("bno") Long bno, Model model) {
			log.info(">BoardController.get() - GET");
			BoardVO boardVO = this.boardService.get(bno);
			
			model.addAttribute("boardVO", boardVO);
			return "/board/get";
		}
	*/
	
	// 커맨드객체 == BoardVO boardVO 에 자동저장
	// "/board/modify" + POST
	@PostMapping(value = "/modify")
	public String modify(BoardVO boardVO, RedirectAttributes rttr, Criteria criteria) { // 커맨드객체
		log.info(">BoardController.modify() - POST");
		if (this.boardService.modify(boardVO)) {
				rttr.addFlashAttribute("result", "SUCCESS");
			}	
			/*
			rttr.addAttribute("bno",boardVO.getBno());
			
			rttr.addAttribute("pageNum", criteria.getPageNum());
			rttr.addAttribute("amount", criteria.getAmount());
			rttr.addAttribute("keyword", criteria.getKeyword());
			rttr.addAttribute("type", criteria.getType());
		};
		
		//[1]
		//return String.format("redirect:/board/get?bno=%d", boardVO.getBno());
		//[2]
		return "redirect:/board/get";
		*/
		String queryString = criteria.getListLink();
		return String.format("redirect:/board/get%s&bno=%d", queryString, boardVO.getBno());			
	}
	
	@GetMapping(value = "/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr, Criteria criteria) { //@안남아있음
		log.info(">BoardController.remove() - GET");
		if(this.boardService.remove(bno)){
			rttr.addFlashAttribute("result", "REMOVESUCCESS");			
			rttr.addFlashAttribute("bno", bno);	// 1회성 세션
			//rttr.addAttribute("bno", bno); // ?bno=7

	         int totalPages = (int)(Math.ceil((double)this.boardService.getTotal(criteria)/criteria.getAmount()));
	         if( criteria.getPageNum() > totalPages ) criteria.setPageNum(totalPages == 0 ? 1 : totalPages);
			rttr.addAttribute("pageNum", criteria.getPageNum());
			rttr.addAttribute("amount", criteria.getAmount());
		};
		
		return "redirect:/board/list"; // 리다이렉트 - request 유지안 됨(model.setAttribute X) 
	}
	
} // class
