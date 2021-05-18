package com.pearl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pearl.domain.BoardVO;
import com.pearl.domain.GalleryVO;
import com.pearl.domain.MemberVO;

@Mapper
public interface GalleryMapper {
	List<GalleryVO> list(GalleryVO vo); 
	int selectTotalCount(GalleryVO vo);
	GalleryVO read(int boardNum);
	MemberVO readWriter(Long memNum);
	void insert(GalleryVO vo); 
	int update(BoardVO vo); 
	int delete(int boardNum); 
}
