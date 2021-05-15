package com.pearl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pearl.domain.CustomUser;
import com.pearl.domain.MemberVO;
import com.pearl.service.MemberService;
import com.pearl.service.MyPageService;
import com.pearl.service.UserDetailServiceImpl;

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
	public ModelAndView my(@AuthenticationPrincipal CustomUser customUser, Long memNum) {
		ModelAndView mv = new ModelAndView("/mypage/my");
		//List<BoardVO> mygal = mypageservice.mygallery(vo.getMemNum());
		//getLoginMember(request, mv);
		//mv.addObject("mygal", mygal);
		if(memNum==null) {
			if(customUser==null) {
				mv.setViewName("redirect:/login");
				return mv;
			}
			mv.addObject("meminfo", customUser.getMember());
		} else mv.addObject("meminfo", service.getProfile(memNum));
		return mv;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/mypage/edit")
	public ModelAndView edit(@AuthenticationPrincipal CustomUser customUser) {
		ModelAndView mv = new ModelAndView("/mypage/edit");
		MemberVO member = service.getProfile(customUser.getMember().getMemNum());
		member.setMemPass("");
		mv.addObject("meminfo", member);
		return mv;
	}
	
	
	@PreAuthorize("principal.username == #vo.memEmail")
	@PostMapping("/mypage/editsend")
	 public String Editsend(MemberVO vo) {
		 user.updateUser(vo); 
		 return "redirect:edit";
	}
	 
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/mypage/fundinfo")
	public ModelAndView fundinfo() {
		ModelAndView mv = new ModelAndView("/mypage/fundinfo");
		return mv;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/mypage/subinfo")
	public ModelAndView subinfo() {
		ModelAndView mv = new ModelAndView("/mypage/subinfo");
		return mv;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/mypage/myfund")
	public ModelAndView myfund(@AuthenticationPrincipal CustomUser customUser) {
		ModelAndView mv = new ModelAndView("mypage/myfund");
		mv.addObject("MyfundList", mypageservice.myfundList(customUser.getMember().getMemNum())); 
		return mv;
	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/mypage/donafund")
	public ModelAndView donafund(@AuthenticationPrincipal CustomUser customUser) {
		ModelAndView mv = new ModelAndView("mypage/donafund");
		mv.addObject("donaList", mypageservice.donaList(customUser.getMember().getMemNum()));
		return mv;
	}
	
}
