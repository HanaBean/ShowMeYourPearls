package com.pearl.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pearl.domain.MemberVO;
import com.pearl.service.MemberService;

import lombok.Setter;


@Controller
@RequestMapping("/mypage/*")
public class MypageController {
	
	@Setter(onMethod_ = @Autowired)
	private MemberService service;
	
	@RequestMapping("/my")
	public ModelAndView my(MemberVO vo, Model model,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("mypage/my");
		getLoginMember(request, mv);

		return mv;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(MemberVO vo, Model model,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("mypage/edit");
		getLoginMember(request, mv);
		
		return mv;
	}
	
	
	@PostMapping("/editsend")
	 public String Editsend(MemberVO vo) {
		 service.update(vo); 
		 return "redirect:edit";
	 
	 	}
	 
	
	@GetMapping("/fundinfo")
	public ModelAndView fundinfo() {
		ModelAndView mv = new ModelAndView("mypage/fundinfo");
		return mv;
	}
	
	@RequestMapping("/subinfo")
	public ModelAndView subinfo() {
		ModelAndView mv = new ModelAndView("mypage/subinfo");
		return mv;
	}
	
	public ModelAndView getLoginMember(HttpServletRequest request, ModelAndView mv) {
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		if(member != null) {
			member = service.getProfile(member.getMemNum());
			mv.addObject("meminfo", member);
		} else {
			mv.setViewName("redirect:../log/login");
		}
		return mv;
	}
	
}
