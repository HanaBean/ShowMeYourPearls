package com.pearl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearl.domain.BoardVO;
import com.pearl.domain.MyPageVO;
import com.pearl.mapper.MyPageMapper;

import lombok.Setter;

@Service
public class MyPageServiceImpl implements MyPageService{

	@Setter(onMethod_ = @Autowired)
	private MyPageMapper mapper;
	
	@Override
	public List<MyPageVO> donaList(Long memNum) {
		return mapper.donaList(memNum);
	}

	@Override
	public List<MyPageVO> myfundList(Long memNum) {
		return mapper.myfundList(memNum);
		
	}

	@Override
	public List<BoardVO> mygallery(Long memNum) {
		return mapper.mygallery(memNum);
	}
	
	
	
}
