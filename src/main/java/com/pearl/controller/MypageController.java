package com.pearl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pearl.domain.BoardVO;
import com.pearl.domain.MemberVO;
import com.pearl.service.MemberService;
import com.pearl.service.MyPageService;

import lombok.Setter;


@Controller

public class MypageController {
	
	@Setter(onMethod_ = @Autowired)
	private MemberService service;
	
	@Setter(onMethod_ = @Autowired)
	private MyPageService mypageservice;
	
	
	@RequestMapping("/mypage")
	public ModelAndView my(MemberVO vo, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("mypage/my");
		//List<BoardVO> mygal = mypageservice.mygallery(vo.getMemNum());
		getLoginMember(request, mv);			
		//mv.addObject("mygal", mygal);
		 
		
		return mv;
	}
	
	
	
	
	@RequestMapping("/mypage/edit")
	public ModelAndView edit(MemberVO vo, Model model,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/mypage/edit");
		getLoginMember(request, mv);
		return mv;
	}
	
	
	@PostMapping("/mypage/editsend")
	 public String Editsend(MemberVO vo) {
		 service.update(vo); 
		 return "redirect:edit";
	 
	 	}
	 
	
	@GetMapping("/mypage/fundinfo")
	public ModelAndView fundinfo() {
		ModelAndView mv = new ModelAndView("/mypage/fundinfo");
		return mv;
	}
	
	@RequestMapping("/mypage/subinfo")
	public ModelAndView subinfo() {
		ModelAndView mv = new ModelAndView("/mypage/subinfo");
		return mv;
	}
	
	@RequestMapping("/mypage/myfund")
	public ModelAndView myfund(@ModelAttribute(value="memNum") Long memNum, Model model) {
		ModelAndView mv = new ModelAndView("mypage/myfund");
		mv.addObject("MyfundList", mypageservice.myfundList(memNum)); 
		
		return mv;
	
	}
	
	@RequestMapping("/mypage/donafund")
	public ModelAndView donafund(@ModelAttribute(value="memNum") Long memNum, Model model) {
		ModelAndView mv = new ModelAndView("mypage/donafund");
		mv.addObject("donaList", mypageservice.donaList(memNum));
		
		return mv;
	}
	

	
	
	public ModelAndView getLoginMember(HttpServletRequest request, ModelAndView mv) {
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		if(member != null) {
			member = service.getProfile(member.getMemNum());
			mv.addObject("meminfo", member);
		} else {
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
}
