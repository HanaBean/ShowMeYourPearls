package com.pearl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearl.domain.BoardVO;
import com.pearl.mapper.BoardMapper;

import lombok.Setter;

@Service
public class BoardServiceImpl implements BoardService{
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;

	@Override
	public List<BoardVO> list() {
		return mapper.list();
	}

	@Override
	public BoardVO read(int bNum) {
		return mapper.read(bNum);
	}

	@Override
	public int insert(BoardVO vo) {
		return mapper.insert(vo);
	}

	@Override
	public int update(BoardVO vo) {
		return mapper.update(vo);
	}

	@Override
	public int delete(int bNum) {
		return mapper.delete(bNum);
	}

	
}
