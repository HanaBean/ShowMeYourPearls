package com.pearl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.Setter;

@Controller
@RequestMapping("/gallery/*")
public class GalleryController {
	
	@RequestMapping("/gallery")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("gallery/gallery");
		
		return mv;
	}
}
