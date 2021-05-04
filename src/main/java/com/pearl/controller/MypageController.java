package com.pearl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pearl.domain.MemberVO;
import com.pearl.service.MemberService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/mypage/*")
public class MypageController {
	
	@Setter(onMethod_ = @Autowired)
	private MemberService service;
	
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
	
	@RequestMapping("/fundinfo")
	public ModelAndView fundinfo() {
		ModelAndView mv = new ModelAndView("mypage/fundinfo");
		return mv;
	}
	
	@RequestMapping("/subinfo")
	public ModelAndView subinfo() {
		ModelAndView mv = new ModelAndView("mypage/subinfo");
		return mv;
	}
	
	
	
	/*
	 * @PostMapping("/edit") public String updateForm(MemberVO vo) { log.info(vo);
	 * service.update(vo); return "redirect:/mypage/edit"; }
	 */

	
}
