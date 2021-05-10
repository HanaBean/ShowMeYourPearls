package com.pearl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearl.domain.BoardVO;
import com.pearl.mapper.MainMapper;

import lombok.Setter;

@Service
public class MainServiceImpl implements MainService{
	
	@Setter(onMethod_ = @Autowired)
	private MainMapper mapper;

	@Override
	public List<BoardVO> list() {
		return mapper.list();
	}

	@Override
	public int get(BoardVO vo) {
		return mapper.get(vo);
	}

	@Override
	public BoardVO writer(BoardVO vo) {
		return mapper.writer(vo);
	}
	
}
