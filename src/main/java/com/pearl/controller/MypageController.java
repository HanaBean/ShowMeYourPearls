package com.pearl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mypage/*")
public class MypageController {
	
	
	@RequestMapping("/my")
	public ModelAndView my() {
		ModelAndView mv = new ModelAndView("mypage/my");
		return mv;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit() {
		ModelAndView mv = new ModelAndView("mypage/edit");
		return mv;
	}

	
}
