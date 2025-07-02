package org.doit.ik.controller;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping(value = "/list")
	public void list(Model model) {
		log.info(">BoardController.list()");
		model.addAttribute("list", this.boardService.getList());
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
	public void get(@RequestParam("bno") Long bno, Model model) {
		log.info(">BoardController.get() - GET");
		BoardVO boardVO = this.boardService.get(bno);
		
		model.addAttribute("boardVO", boardVO);
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
	public String modify(BoardVO boardVO, RedirectAttributes rttr) { // 커맨드객체
		log.info(">BoardController.modify() - POST");
		if (this.boardService.modify(boardVO)) {
			rttr.addFlashAttribute("result", "SUCCESS");			
		};
		
		//[1]
		//return String.format("redirect:/board/get?bno=%d", boardVO.getBno());
		//[2]
		rttr.addAttribute("bno",boardVO.getBno());
		return "redirect:/board/get";
	}
	
	@GetMapping(value = "/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		log.info(">BoardController.remove() - GET");
		if(this.boardService.remove(bno)){
			rttr.addFlashAttribute("result", "REMOVESUCCESS");			
			rttr.addFlashAttribute("bno", bno);	// 1회성 세션
			//rttr.addAttribute("bno", bno); // ?bno=7
		};
		
		return "redirect:/board/list";
	}
	
} // class
