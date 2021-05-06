package com.pearl.service;

import java.util.HashMap;
import java.util.List;

import com.pearl.domain.BoardVO;
import com.pearl.domain.GalleryVO;
import com.pearl.domain.MemberVO;


public interface GalleryService {
	List<GalleryVO> list(); 
	BoardVO read(int boardNum); 
	MemberVO readWriter(Long memNum);
	int insert(BoardVO vo); 
	int update(BoardVO vo); 
	int delete(int boardNum); 
}
