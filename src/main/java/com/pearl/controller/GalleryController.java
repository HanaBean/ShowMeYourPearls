package com.pearl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pearl.domain.BoardVO;
import com.pearl.domain.EmotionVO;
import com.pearl.service.EmotionService;
import com.pearl.service.GalleryService;

import lombok.Setter;

@Controller
@RequestMapping("/gallery/*")
public class GalleryController {
	
	@Setter(onMethod_ = @Autowired)
	private GalleryService service;
	
	@Setter(onMethod_ = @Autowired)
	private EmotionService emotion;
	
	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("gallery/gallery");
		List<BoardVO> list = service.list();
		
		mv.addObject("gallery", list);
		return mv;
	}
	
	@RequestMapping("/get")
	public ModelAndView get(int boardNum) {
		ModelAndView mv = new ModelAndView("gallery/get");
		BoardVO board = service.read(boardNum);
		mv.addObject("gallery", board);
		mv.addObject("writer", service.readWriter(board.getMemNum()));
		List<EmotionVO> emo = emotion.emoCount(boardNum);
		for(int i=0;i<emo.size();i++) {
			EmotionVO vo = emo.get(i);
			mv.addObject(vo.getEmoExpress(), vo.getEmoCount());
		}
		//mv.addObject("emotion", emotion.emoCount(boardNum));
		return mv;
	}
	
	@RequestMapping("/write")
	public ModelAndView write() {
		return new ModelAndView("gallery/write");
	}
	
	@PostMapping("/register")
	public ModelAndView register(BoardVO vo) {
		ModelAndView mv = new ModelAndView("redirect:/gallery/list");
		service.insert(vo);
		return mv;
	}
	
	@GetMapping("/modify")
	public ModelAndView modify(int boardNum) {
		ModelAndView mv = new ModelAndView("gallery/modify");
		mv.addObject("gallery", service.read(boardNum));
		return mv;
	}
	
	@PostMapping("/modify")
	public ModelAndView modify(BoardVO vo) {
		ModelAndView mv = new ModelAndView();
		service.update(vo);
		mv.setViewName("redirect:/gallery/get?boardNum="+vo.getBoardNum());
		return mv;
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(int boardNum) {
		ModelAndView mv = new ModelAndView();
		service.delete(boardNum);
		mv.setViewName("redirect:/gallery/list");
		return mv;
	}
	
	@RequestMapping(value="/emotion", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> emotion(@RequestBody EmotionVO vo) throws Exception{
		System.out.println("vo : "+vo.getBoardNum()+","+vo.getMemNum()+","+vo.getEmoExpress());
		EmotionVO emo = emotion.getEmo(vo);
		if(emo==null) {
			emotion.emotionInsert(vo);
		} else {
			emotion.updateEmo(vo);
		}
		return new ResponseEntity<String>(vo.getEmoExpress(), HttpStatus.OK);
	}
	
}
