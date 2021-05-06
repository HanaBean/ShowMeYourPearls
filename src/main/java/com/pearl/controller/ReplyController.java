package com.pearl.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pearl.domain.ReplyVO;
import com.pearl.service.ReplyService;

import lombok.Setter;

@Controller
@RequestMapping("/reply/*")
public class ReplyController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Setter(onMethod_ =@Autowired)
	private ReplyService service;
	
	@PostMapping("/upload")
	public ResponseEntity<String> upload(ReplyVO vo){
		service.insert(vo);
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
	
	@GetMapping("/{boardNum}")
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("boardNum") int boardNum){
		List<ReplyVO> list = service.list(boardNum);
		return new ResponseEntity<List<ReplyVO>>(list,HttpStatus.OK);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam("replyNum") int replyNum) {
		log.info("Delete>>>>>>>>"+replyNum);
		service.delete(replyNum);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
}
