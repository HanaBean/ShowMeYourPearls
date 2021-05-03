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
	
	
	
	@GetMapping({"/get","/modify"})
	public ModelAndView get(int fundNum) {
		ModelAndView mv = new ModelAndView("/fund/fundGeting");
		FundVO detail = service.get(fundNum);
		mv.addObject("detail", detail);
		return mv;
	}
	
	@PostMapping("/modify")
	public String modify(FundVO vo) {
		service.update(vo);
		return "redirect:/fund/fundlist";
	}
	
	@GetMapping("/delete")
	public String delete(int mno) {
		service.delete(mno);
		return "redirect:/fund/fundlist";
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
}
