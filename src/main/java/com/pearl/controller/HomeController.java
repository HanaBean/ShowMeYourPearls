package com.pearl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pearl.domain.AdminBoardVO;
import com.pearl.domain.FundVO;
import com.pearl.domain.GalleryVO;
import com.pearl.service.MainService;
import com.pearl.service.MemberService;

import lombok.Setter;

@Controller

public class HomeController {

	@Setter(onMethod_ = @Autowired)
	private MainService mainservice;

	@RequestMapping("/")
	public ModelAndView main(Model model, Long fundNum, Long boardNum) {
		ModelAndView mv = new ModelAndView("/main");
		List<GalleryVO> gallery = mainservice.list();
		List<FundVO> fund = mainservice.fundlist();
		mv.addObject("list", gallery);
		mv.addObject("fund", fund);
		return mv;
	}


}
