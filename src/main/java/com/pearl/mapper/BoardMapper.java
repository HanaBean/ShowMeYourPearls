package com.pearl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pearl.domain.BoardVO;

@Mapper
public interface BoardMapper {
	List<BoardVO> list(); 
	BoardVO read(int bNum); 
	int insertNotice(BoardVO vo); 
	int update(BoardVO vo); 
	int delete(int bNum); 
}
