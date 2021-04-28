package com.pearl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pearl.domain.BoardVO;
import com.pearl.service.BoardService;

import lombok.Setter;

@Controller
@RequestMapping("/notice/*")
public class NoticeController {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	@RequestMapping("/list")
	public ModelAndView list(Model model) {
		ModelAndView mv = new ModelAndView("notice/list");
		mv.addObject("list", service.list());
		
		return mv;
	}

	@RequestMapping("/read")
	public ModelAndView read(int boardNum, Model model) {
		ModelAndView mv = new ModelAndView("notice/read");
		mv.addObject("notice", service.read(boardNum));
		
		return mv;
	}
	
	@RequestMapping("/write")
	public ModelAndView writePage() {
		ModelAndView mv = new ModelAndView("notice/write");
		
		return mv;
	}
	
	@PostMapping("/write")
	public ModelAndView writeSend(BoardVO vo) {
		ModelAndView mv = new ModelAndView("redirect:/notice/list");
		service.insertNotice(vo);
		return mv;
	}
	
	@RequestMapping("/modify")
	public ModelAndView modifyPage(int boardNum, Model model) {
		ModelAndView mv = new ModelAndView("notice/modify");
		mv.addObject("notice", service.read(boardNum));
		
		return mv;
	}
	
	@PostMapping("/modify")
	public ModelAndView modifySend(BoardVO vo) {
		ModelAndView mv = new ModelAndView("redirect:/notice/list");
		service.update(vo);
		return mv;
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(int boardNum, Model model) {
		ModelAndView mv = new ModelAndView("redirect:/notice/list");
		service.delete(boardNum);
		
		return mv;
	}
}
