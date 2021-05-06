package com.pearl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearl.domain.ReplyVO;
import com.pearl.mapper.ReplyMapper;

import lombok.Setter;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Setter(onMethod_ = @Autowired)
	ReplyMapper mapper;
	
	@Override
	public List<ReplyVO> list(int boardNum) {
		return mapper.list(boardNum);
	}

	@Override
	public ReplyVO get(int memNum) {
		return mapper.get(memNum);
	}
	
	@Override
	public int getCount(int boardNum) {
		return mapper.getCount(boardNum);
	}

	@Override
	public int insert(ReplyVO vo) {
		return mapper.insert(vo);
	}

	@Override
	public int update(ReplyVO vo) {
		return mapper.update(vo);
	}

	@Override
	public int delete(int replyNum) {
		return mapper.delete(replyNum);
	}

}
