package com.pearl.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pearl.domain.CustomUser;
import com.pearl.domain.MemberVO;
import com.pearl.service.MemberService;
import com.pearl.service.MyPageService;
import com.pearl.service.UserDetailServiceImpl;

//github.com/LeeGu-hun/ShowMeYourPearl

import lombok.Setter;


@Controller
public class MypageController {
	
	@Setter(onMethod_ = @Autowired)
	private MemberService service;
	
	@Setter(onMethod_ = @Autowired)
	private MyPageService mypageservice;
	
	@Setter(onMethod_ = @Autowired)
	private UserDetailServiceImpl user;
	
	@RequestMapping("/mypage")
	public ModelAndView my(@AuthenticationPrincipal CustomUser customUser) {
		ModelAndView mv = new ModelAndView("/mypage/my");
		//List<BoardVO> mygal = mypageservice.mygallery(vo.getMemNum());
		//getLoginMember(request, mv);
		//mv.addObject("mygal", mygal);
		mv.addObject("meminfo", customUser.getMember());
		return mv;
	}
	
	
	
	
	@RequestMapping("/mypage/edit")
	public ModelAndView edit(@AuthenticationPrincipal CustomUser customUser) {
		ModelAndView mv = new ModelAndView("/mypage/edit");
		MemberVO member = service.getProfile(customUser.getMember().getMemNum());
		mv.addObject("meminfo", member);
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
	public ModelAndView myfund(@AuthenticationPrincipal CustomUser customUser) {
		ModelAndView mv = new ModelAndView("mypage/myfund");
		mv.addObject("MyfundList", mypageservice.myfundList(customUser.getMember().getMemNum())); 
		mv.addObject("memNum", customUser.getMember().getMemNum());
		return mv;
	
	}
	
	@RequestMapping("/mypage/donafund")
	public ModelAndView donafund(@AuthenticationPrincipal CustomUser customUser) {
		ModelAndView mv = new ModelAndView("mypage/donafund");
		mv.addObject("donaList", mypageservice.donaList(customUser.getMember().getMemNum()));
		mv.addObject("memNum", customUser.getMember().getMemNum());
		
		return mv;
	}
	

	
	
//	public ModelAndView getLoginMember(HttpServletRequest request, ModelAndView mv) {
//		HttpSession session = request.getSession();
//		MemberVO member = (MemberVO) session.getAttribute("member");
//		if(member != null) {
//			member = service.getProfile(member.getMemNum());
//			mv.addObject("meminfo", member);
//		} else {
//			mv.setViewName("redirect:/login");
//		}
//		return mv;
//	}
	
}
