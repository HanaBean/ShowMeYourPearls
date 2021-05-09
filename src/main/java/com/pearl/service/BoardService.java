package com.pearl.service;

import com.pearl.domain.BoardVO;

public interface BoardService {
	int get(BoardVO vo);
	BoardVO writer(BoardVO vo);
}
