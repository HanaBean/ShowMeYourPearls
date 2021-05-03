package com.pearl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pearl.service.BoardService;

import lombok.Setter;

@Controller
@RequestMapping("/mypage/*")
public class MypageController {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	@RequestMapping("/my")
	public ModelAndView list(Model model) {
		ModelAndView mv = new ModelAndView("mypage/my");
		mv.addObject("my", service.list());
		return mv;
	}

	
}
