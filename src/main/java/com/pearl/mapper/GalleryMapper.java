package com.pearl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pearl.domain.BoardVO;
import com.pearl.domain.MemberVO;

@Mapper
public interface GalleryMapper {
	List<BoardVO> list(); 
	BoardVO read(int boardNum);
	MemberVO readWriter(Long memNum);
	int insert(BoardVO vo); 
	int update(BoardVO vo); 
	int delete(int boardNum); 
}
