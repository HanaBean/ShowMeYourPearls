package com.pearl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pearl.domain.BoardVO;

@Mapper
public interface MainMapper {
	List<BoardVO> list();
	int get(BoardVO vo);
	BoardVO writer(BoardVO vo);
}
