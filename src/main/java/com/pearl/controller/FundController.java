package com.pearl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pearl.domain.FundVO;
import com.pearl.service.FundService;

import lombok.Setter;

@Controller
@RequestMapping("/fund/*")

public class FundController {
	
	@Setter(onMethod_ = @Autowired)
	private FundService service;
	
	@RequestMapping("/fundList")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("/fund/fundList");
		List<FundVO> fund = service.getList();
		mv.addObject("list", fund);
		return mv;
	}

	@GetMapping("/modify")
	public ModelAndView modify(int fundNum) {
		ModelAndView mv = new ModelAndView("/fund/fundUpdate");
		FundVO update = service.get(fundNum);
		mv.addObject("update", update);
		return mv;
	}
	
	@GetMapping("/get")
	public ModelAndView get(int fundNum) {
		ModelAndView mv = new ModelAndView("/fund/fundGeting");
		FundVO detail = service.get(fundNum);
		mv.addObject("detail", detail);
		return mv;
	}
	
	@PostMapping("/modify")
	public ModelAndView modify(FundVO vo) {
		ModelAndView mv = new ModelAndView("redirect:/fund/get?fundNum="+ vo.getFundNum());
		service.update(vo);		
		return mv;
	}
	
	@GetMapping("/delete")
	public String delete(int fundNum) {
		service.delete(fundNum);
		return "redirect:/fund/fundList";
	}
	
	@RequestMapping("/write")
	public ModelAndView fundWrite() {
		ModelAndView mv = new ModelAndView("fund/fundWrite");
		return mv;
	}
	
	@PostMapping("/write")
	public ModelAndView fundWrite(FundVO vo) {
		ModelAndView mv = new ModelAndView("redirect:/fund/fundList");
		service.insert(vo);
		return mv;
	}
	
	@RequestMapping("/pay")
	public ModelAndView fundPay() {
		ModelAndView mv = new ModelAndView("fund/fundPay");
		return mv;
	}
	
	@PostMapping("/pay")
	public ModelAndView fundPay(FundVO vo) {
		ModelAndView mv = new ModelAndView("redirect:/fund/get?fundNum="+ vo.getFundNum());
		service.insert(vo);
		return mv;
	}
}
