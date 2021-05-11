package com.pearl.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pearl.domain.BoardVO;
import com.pearl.domain.EmotionVO;
import com.pearl.domain.GalleryVO;
import com.pearl.domain.MemberVO;
import com.pearl.paging.Criteria;

@Mapper
public interface GalleryMapper {
	List<GalleryVO> list(GalleryVO vo); 
	int selectTotalCount(GalleryVO vo);
	BoardVO read(int boardNum);
	MemberVO readWriter(Long memNum);
	int insert(BoardVO vo); 
	int update(BoardVO vo); 
	int delete(int boardNum); 
}
