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
	
	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("gallery/gallery");
		
		return mv;
	}
	
	@RequestMapping("/get")
	public ModelAndView get() {
		ModelAndView mv = new ModelAndView("gallery/get");
		
		return mv;
	}
	
	@RequestMapping("/write")
	public ModelAndView write() {
		ModelAndView mv = new ModelAndView("gallery/write");
		
		return mv;
	}
	
	@RequestMapping("/modify")
	public ModelAndView modify() {
		ModelAndView mv = new ModelAndView("gallery/modify");
		
		return mv;
	}
}
