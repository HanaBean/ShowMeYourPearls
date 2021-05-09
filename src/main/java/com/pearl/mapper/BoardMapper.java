package com.pearl.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pearl.domain.BoardVO;


@Mapper
public interface BoardMapper {
	int get(BoardVO vo);
	BoardVO writer(BoardVO vo);
}
