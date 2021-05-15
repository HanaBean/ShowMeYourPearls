package com.pearl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pearl.domain.MemberVO;
import com.pearl.service.UserDetailServiceImpl;

import lombok.Setter;


@Controller
public class LoginController { 
	
	@Setter(onMethod_ =@Autowired)
	private UserDetailServiceImpl user;
	
	@GetMapping("/login")
	public void login() {}
	
	@PostMapping("/join")
	public String insertForm(MemberVO vo) {
		user.joinUser(vo);
		return "redirect:/login";
	}
	
	@RequestMapping("/denied")
	public void dednied() {}
	
}
