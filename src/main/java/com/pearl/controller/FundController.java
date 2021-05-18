package com.pearl.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.pearl.domain.FundVO;
import com.pearl.domain.RewardVO;
import com.pearl.service.FundService;
import com.pearl.service.RewardService;

import lombok.Setter;

@Controller
@RequestMapping("/fund/*")

public class FundController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Setter(onMethod_ = @Autowired)
	private FundService service;
	
	@Setter(onMethod_ = @Autowired)
	private RewardService rwService;
	
	
	@RequestMapping("/fundList")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("/fund/fundList");
		List<FundVO> fund = service.getList();
		mv.addObject("list", fund);
		return mv;
	}

	@GetMapping("/modify")
	public ModelAndView modify(Long fundNum) {
		ModelAndView mv = new ModelAndView("/fund/fundUpdate");
		FundVO update = service.get(fundNum);
		mv.addObject("update", update);
		return mv;
	}
	
	@GetMapping("/get")
	public ModelAndView get(Long fundNum) {
		ModelAndView mv = new ModelAndView("/fund/fundGeting");
		FundVO detail = service.get(fundNum);
		List<RewardVO> itemList = rwService.getListReward(fundNum);
		mv.addObject("detail", detail);
		mv.addObject("rewardList", itemList);
		return mv;
	}
	
	@RequestMapping("/getPay")
	public ModelAndView get(RewardVO rwvo, FundVO vo) {
		ModelAndView mv = new ModelAndView("fund/fundPay");
		mv.addObject("fund", vo);
		mv.addObject("reward", rwvo);
		return mv;
	}
	
	@PostMapping("/modify")
	public ModelAndView modify(FundVO vo) {
		ModelAndView mv = new ModelAndView("redirect:/fund/get?fundNum="+ vo.getFundNum());
		service.update(vo);		
		return mv;
	}
	
	@GetMapping("/delete")
	public String delete(Long fundNum) {
		service.delete(fundNum);
		return "redirect:/fund/fundList";
	}
	
	@RequestMapping("/write")
	public ModelAndView fundWrite() {
		ModelAndView mv = new ModelAndView("fund/fundWrite");
		return mv;
	}
	
	@PostMapping("/writeFund")
	public String fundWrite(FundVO vo
			, @RequestParam(value="itemList") ArrayList<RewardVO> rewardList
			, MultipartHttpServletRequest mt) throws Exception {
		
		service.insert(vo, rewardList, mt);
		
		return "redirect:/fund/fundList";
	}
	
	@PostMapping("/pay")
	public ModelAndView fundPay(FundVO vo) {
		ModelAndView mv = new ModelAndView("redirect:/fund/get?fundNum="+ vo.getFundNum());
//		service.insert(vo);
		return mv;
	}
}
