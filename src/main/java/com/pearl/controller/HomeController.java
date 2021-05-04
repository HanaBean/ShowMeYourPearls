package com.pearl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home/*")
public class HomeController {

	@RequestMapping("/main")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView("home/main");
		return mv;
	}
	

}