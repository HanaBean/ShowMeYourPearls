package com.pearl.service;

import java.util.List;

import com.pearl.domain.BoardVO;

public interface MainService {
	List<BoardVO> list();
	int get(BoardVO vo);
	BoardVO writer(BoardVO vo);
}
