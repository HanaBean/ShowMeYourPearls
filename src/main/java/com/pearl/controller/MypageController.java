package com.pearl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mypage/*")
public class MypageController {
	
	@RequestMapping("/my")
	public ModelAndView list(Model model) {
		ModelAndView mv = new ModelAndView("mypage/my");
		return mv;
	}

	
}
