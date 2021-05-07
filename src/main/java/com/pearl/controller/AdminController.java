package com.pearl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pearl.domain.AdminPaymentVO;
import com.pearl.domain.MemberVO;
import com.pearl.domain.ReplyVO;
import com.pearl.domain.SearchVO;
import com.pearl.service.AdminService;
import com.pearl.service.MemberService;

import lombok.Setter;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	
	@Setter(onMethod_ = @Autowired)
	private MemberService memberService;
	
	@Setter(onMethod_ = @Autowired)
	private AdminService adminService;
	
	@RequestMapping("/member")
	public ModelAndView member(Model model) {
		ModelAndView mv = new ModelAndView("admin/member");
		mv.addObject("list", memberService.list());
		
		return mv;
	}
	
	@RequestMapping("/member/profile")
	public ModelAndView profile(Model model, Long memNum) {
		ModelAndView mv = new ModelAndView("admin/profile");
		mv.addObject("vo", memberService.getProfile(memNum));
		
		return mv;
	}
	
	@RequestMapping("/member/profileModify")
	public ModelAndView profileModify(Model model, Long memNum) {
		ModelAndView mv = new ModelAndView("admin/profileModify");
		mv.addObject("vo", memberService.getProfile(memNum));
		
		return mv;
	}
	
	@PostMapping("/member/profileModify")
	public ModelAndView profileModifySend(MemberVO vo, Long memNum) {
		ModelAndView mv = new ModelAndView("redirect:/admin/member/profile?memNum=" + memNum);
		memberService.update(vo);
		
		return mv;
	}
	
	@RequestMapping("/member/delete")
	public ModelAndView delete(Model model, Long memNum[]) {
		ModelAndView mv = new ModelAndView("redirect:/admin/member");
		adminService.memberDelete(memNum);
		
		return mv;
	}
	
	//
	
	
	@RequestMapping("/post")
	public ModelAndView post(Model model) {
		ModelAndView mv = new ModelAndView("admin/post");
		mv.addObject("list", adminService.boardList());
		
		return mv;
	}
	
	@RequestMapping("/post/delete")
	public ModelAndView postDelete(Model model, Long boardNum[]) {
		ModelAndView mv = new ModelAndView("redirect:/admin/post");
		adminService.postDelete(boardNum);
		
		return mv;
	}
	
	@RequestMapping("/fund")
	public ModelAndView fund(Model model) {
		ModelAndView mv = new ModelAndView("admin/fund");
		mv.addObject("list", adminService.fundList());
		
		return mv;
	}
	
	@RequestMapping("/fund/delete")
	public ModelAndView fundDelete(Model model, Long fundNum[]) {
		ModelAndView mv = new ModelAndView("redirect:/admin/fund");
		adminService.fundDelete(fundNum);
		
		return mv;
	}
	
	@RequestMapping("/payment")
	public ModelAndView payment(Model model) {
		ModelAndView mv = new ModelAndView("admin/payment");
		mv.addObject("list", adminService.paymentList());
		
		return mv;
	}
	
//	@ResponseBody
//	@RequestMapping("/searchPayment")
//	public ResponseEntity<List<AdminPaymentVO>> searchPaymentList(Model model, @RequestBody SearchVO vo) {
//		List<AdminPaymentVO> list = adminService.searchPaymentList(vo);
//		return new ResponseEntity<List<AdminPaymentVO>>(list,HttpStatus.OK);
//	}
	
//	@RequestMapping("/payment/delete")
//	public ModelAndView paymentDelete(Model model, Long fundNum[]) {
//		ModelAndView mv = new ModelAndView("redirect:/admin/payment");
//		adminService.fundDelete(fundNum);
//		
//		return mv;
//	}
}
