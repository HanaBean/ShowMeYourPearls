package com.pearl.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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


@Controller
@RequestMapping("/log/*")
public class LoginController { 
	
	@Setter(onMethod_ = @Autowired)
	private MemberService service;
	
	private MemberVO member = new MemberVO();
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("log/login");
		MemberVO member = new MemberVO();
		mv.addObject("cklog", member);

		return mv;
	}
	
	@PostMapping("/join")
	public String insertForm(MemberVO vo) {
		service.insert(vo);
		return "redirect:/log/login";
	}
	
	@PostMapping("/login")
	public ModelAndView LoginForm(MemberVO vo, HttpServletRequest request) {
		ModelAndView log = new ModelAndView("log/login");
		member = service.get(vo);
		HttpSession session = request.getSession(true);
		
		if(member == null) {
			session.setAttribute("member", null);
			log.addObject("cklog", "alert");
		} else {
			session.setAttribute("member", member);
			log.setViewName("redirect:../mypage/edit");
		}
		return log;
	}
	
}
