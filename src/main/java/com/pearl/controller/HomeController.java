package com.pearl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pearl.service.BoardService;

import lombok.Setter;

@Controller
@RequestMapping("/home/*")
public class HomeController {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	

	@RequestMapping("/main")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView("home/main");
		mv.addObject("main", service.list());
		return mv;
	}
	

}
