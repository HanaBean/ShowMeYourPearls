package com.pearl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearl.domain.RewardVO;
import com.pearl.mapper.RewardMapper;

import lombok.Setter;

@Service
public class RewardServiceImpl implements RewardService{
	
	@Setter(onMethod_ = @Autowired)
	private RewardMapper mapper;

	@Override
	public List<RewardVO> getList() {
		// TODO Auto-generated method stub
		return mapper.getList();
	}

	@Override
	public int insert(RewardVO vo) {
		// TODO Auto-generated method stub
		return mapper.insert(vo);
	}

	@Override
	public int update(RewardVO vo) {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Override
	public int delete(int rwrdNum) {
		// TODO Auto-generated method stub
		return mapper.delete(rwrdNum);
	}

	
	
}
