package com.pearl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.pearl.domain.MemberVO;
import com.pearl.service.MemberService;
import com.pearl.service.UserDetailServiceImpl;

import lombok.Setter;


@Controller
public class LoginController { 
	
	@Setter(onMethod_ = @Autowired)
	private MemberService service;
	
	@Setter(onMethod_ =@Autowired)
	private UserDetailServiceImpl login;
	
	private MemberVO member = new MemberVO();
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("/login");
		MemberVO member = new MemberVO();
		mv.addObject("cklog", member);

		return mv;
	}
	
	@PostMapping("/join")
	public String insertForm(MemberVO vo) {
		login.joinUser(vo);
		return "redirect:/login";
	}
	
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@RequestMapping("/denied")
	public ModelAndView dednied(String message) {
		ModelAndView mv = new ModelAndView("/denied");
		mv.addObject("message", message);
		return mv;
	}
	
//	@PostMapping("/login")
//	public ModelAndView LoginForm(MemberVO vo, HttpServletRequest request) {
//		ModelAndView log = new ModelAndView("/login");
//		member = service.get(vo);
//		HttpSession session = request.getSession(true);
//		
//		if(member == null) {
//			session.setAttribute("member", null);
//			log.addObject("cklog", "alert");
//		} else {
//			session.setAttribute("member", member);
//			log.setViewName("redirect:mypage/edit");
//		}
//		return log;
//	}
	
	
}
