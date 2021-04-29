package com.pearl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearl.domain.BoardVO;
import com.pearl.domain.MemberVO;
import com.pearl.mapper.GalleryMapper;

import lombok.Setter;

@Service
public class GalleryServiceImpl implements GalleryService{
	@Setter(onMethod_ = @Autowired)
	private GalleryMapper mapper;

	@Override
	public List<BoardVO> list() {
		return mapper.list();
	}

	@Override
	public BoardVO read(int boardNum) {
		return mapper.read(boardNum);
	}
	
	@Override
	public MemberVO readWriter(Long memNum) {
		return mapper.readWriter(memNum);
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
	public int delete(int boardNum) {
		return mapper.delete(boardNum);
	}

	
}
