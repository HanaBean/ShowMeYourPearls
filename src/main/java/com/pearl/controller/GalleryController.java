package com.pearl.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pearl.domain.BoardVO;
import com.pearl.domain.CustomUser;
import com.pearl.domain.EmotionVO;
import com.pearl.domain.GalleryVO;
import com.pearl.domain.PictureVO;
import com.pearl.service.EmotionService;
import com.pearl.service.GalleryService;

import lombok.Setter;

@Controller
@RequestMapping("/gallery/*")
public class GalleryController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Setter(onMethod_ = @Autowired)
	private GalleryService service;
	
	@Setter(onMethod_ = @Autowired)
	private EmotionService emotion;
	
	@RequestMapping("/list")
	public ModelAndView list(@ModelAttribute("vo") GalleryVO vo) {
		ModelAndView mv = new ModelAndView("gallery/gallery");
		List<GalleryVO> list;
		list = service.list(vo);
		mv.addObject("gallery", list);
		return mv;
	}
	
	@RequestMapping("/get")
	public ModelAndView get(int boardNum) {
		ModelAndView mv = new ModelAndView("gallery/get");
		GalleryVO board = service.read(boardNum);
		mv.addObject("gallery", board);
		mv.addObject("writer", service.readWriter(board.getMemNum()));
		List<EmotionVO> emo = emotion.emoCount(boardNum);
		for(int i=0;i<emo.size();i++) {
			EmotionVO vo = emo.get(i);
			mv.addObject(vo.getEmoExpress(), vo.getEmoCount());
		}
		return mv;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/write")
	public ModelAndView write() {
		return new ModelAndView("gallery/write");
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/register")
	public ModelAndView register(GalleryVO vo,@RequestParam("file") MultipartFile file,
			@AuthenticationPrincipal CustomUser user) {
		ModelAndView mv = new ModelAndView("redirect:/gallery/list");
		vo.setMemNum(user.getMember().getMemNum());
		
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
		
		vo.setPicture(picture);
		service.insert(vo);
		return mv;
	}
	
	@PreAuthorize("principal.username == #memEmail")
	@GetMapping("/modify")
	public ModelAndView modify(int boardNum, String memEmail) {
		ModelAndView mv = new ModelAndView("gallery/modify");
		mv.addObject("gallery", service.read(boardNum));
		mv.addObject("writer", memEmail);
		return mv;
	}
	
	@PreAuthorize("principal.username == #memEmail")
	@PostMapping("/modify")
	public ModelAndView modify(BoardVO vo, String memEmail) {
		ModelAndView mv = new ModelAndView();
		service.update(vo);
		mv.setViewName("redirect:/gallery/get?boardNum="+vo.getBoardNum());
		return mv;
	}
	
	@PreAuthorize("principal.username == #memEmail")
	@RequestMapping("/delete")
	public ModelAndView delete(int boardNum, String memEmail) {
		ModelAndView mv = new ModelAndView();
		service.delete(boardNum);
		mv.setViewName("redirect:/gallery/list");
		return mv;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value="/emotion", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public ResponseEntity<List<EmotionVO>> emotion(@RequestBody EmotionVO vo) throws Exception{
		log.info("vo : "+vo.getBoardNum()+","+vo.getMemNum()+","+vo.getEmoExpress());
		
		EmotionVO emo = emotion.getEmo(vo);
		if(emo==null) {
			emotion.emotionInsert(vo);
		} else if(!vo.getEmoExpress().equals(emo.getEmoExpress())){
			emotion.updateEmo(vo);
		}
		
		List<EmotionVO> emoCnt = emotion.emoCount(vo.getBoardNum().intValue());
		
		return new ResponseEntity<List<EmotionVO>>(emoCnt,HttpStatus.OK);
	}
	
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName) {
		File file = new File("c:\\pearl\\"+fileName);
		log.info("file>>>>>>>>"+file);
		ResponseEntity<byte[]> result = null;
		HttpHeaders header = new HttpHeaders();
		try {
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<byte[]>(
					FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(new Date());
		return str.replace("-", File.separator); //separator:폴더와 폴더의 구분자
	}
	
}
