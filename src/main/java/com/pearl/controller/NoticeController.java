package com.pearl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping("/write")
	public ModelAndView write() {
		ModelAndView mv = new ModelAndView("notice/write");
		
		return mv;
	}
	
	@RequestMapping("/read")
	public ModelAndView read() {
		ModelAndView mv = new ModelAndView("notice/read");
		
		return mv;
	}
}
