package com.pearl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pearl.service.MemberService;

import lombok.Setter;

@Controller

public class HomeController {
	
	@Setter(onMethod_ = @Autowired)
	private MemberService memberService;
	
	

	@RequestMapping("/")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView("home/main");
		return mv;
	}
	
	/*
	 * @RequestMapping() public ModelAndView gallery(int boardNum, Model model) {
	 * 
	 * }
	 */
}
