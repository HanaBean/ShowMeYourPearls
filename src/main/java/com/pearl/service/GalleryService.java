package com.pearl.service;

import java.util.List;

import com.pearl.domain.BoardVO;
import com.pearl.domain.EmotionVO;
import com.pearl.domain.MemberVO;


public interface GalleryService {
	List<BoardVO> list(); 
	BoardVO read(int boardNum); 
	MemberVO readWriter(Long memNum);
	int insert(BoardVO vo); 
	int update(BoardVO vo); 
	int delete(int boardNum); 
	int emotionInsert(EmotionVO vo);
	EmotionVO getEmo(EmotionVO vo);
	int updateEmo(EmotionVO vo);
}
