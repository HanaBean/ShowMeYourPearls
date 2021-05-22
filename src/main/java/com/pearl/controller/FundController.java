package com.pearl.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pearl.domain.FundVO;
import com.pearl.domain.PictureVO;
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
	public ModelAndView get(FundVO vo) {
		ModelAndView mv = new ModelAndView("/fund/fundPay");
		log.info("fund>>>>>>>>>>"+vo.getRwvo());
		RewardVO[] rwvo = vo.getRwvo();
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
	public ModelAndView fundWrite(FundVO vo
			, @RequestParam(value="itemList") ArrayList<RewardVO> rewardList
			, @RequestParam("file") MultipartFile file) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/fund/fundList");
		vo.setPic(uploadPicture(file));
		service.insert(vo);
		
		return mv;
	}
	
	@PostMapping("/pay")
	public ModelAndView fundPay(FundVO vo) {
		ModelAndView mv = new ModelAndView("redirect:/fund/get?fundNum="+ vo.getFundNum());
		service.insert(vo);
		return mv;
	}
	
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(new Date());
		return str.replace("-", File.separator); //separator:폴더와 폴더의 구분자
	}
	
	private PictureVO uploadPicture(MultipartFile file) {
		PictureVO picture = new PictureVO();
		String uploadFolder = "c:\\pearl";
		
		//저장 경로를 File객체에 담음. 파일이 아닌 디렉토리
		File uploadPath = new File(uploadFolder, getFolder());
		log.info("uploadPath: "+uploadPath);
		if(uploadPath.exists()==false) uploadPath.mkdirs();
		
		log.info("uploadFile Name :" + file.getOriginalFilename());
		log.info("uploadFile Size :" + file.getSize());
		
		String uploadFileName = file.getOriginalFilename();
		
		//IE has file path
		uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
		log.info(uploadFileName);
		picture.setPicName(uploadFileName.substring(0, uploadFileName.lastIndexOf(".")));
		picture.setPicTail(uploadFileName.substring(uploadFileName.lastIndexOf(".")+1));
		
		UUID uuid = UUID.randomUUID();
		uploadFileName = uuid.toString()+"_"+uploadFileName;
		
		File saveFile = new File(uploadPath, uploadFileName);
		try {
			file.transferTo(saveFile);
			picture.setPicUuid(uuid.toString());
			picture.setPicPath(getFolder());
		} catch (Exception e) {
			log.error(e.getMessage());
		} //end catch
		
		return picture;
	}
}
