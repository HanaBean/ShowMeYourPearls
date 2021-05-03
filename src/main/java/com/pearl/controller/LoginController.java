package com.pearl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pearl.domain.MemberVO;
import com.pearl.service.MemberService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/log/*")
public class LoginController { 
	
	@Setter(onMethod_ = @Autowired)
	private MemberService service;
	
	private MemberVO member;
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("log/login");
		MemberVO member = new MemberVO();
		mv.addObject("cklog", member);

		return mv;
	}
	
	@PostMapping("/join")
	public String insertForm(MemberVO vo) {
		log.info(vo);
		service.insert(vo);
		return "redirect:/log/login";
	}
	
	@PostMapping("/login")
	public ModelAndView LoginForm(MemberVO vo) {
		ModelAndView log = new ModelAndView("log/login");
		String logVo = service.get(vo);
		
		if(logVo == null) {
			log.addObject("cklog", "alert");
		} else {
			log.addObject("cklog",logVo);
			log.setViewName("home/main");
		}
		return log;
	}
	
}
