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
	public List<RewardVO> getListReward() {
		// TODO Auto-generated method stub
		return mapper.getListReward();
	}

	@Override
	public int insertReward(RewardVO vo) {
		// TODO Auto-generated method stub
		return mapper.insertReward(vo);
	}

	@Override
	public int updateReward(RewardVO vo) {
		// TODO Auto-generated method stub
		return mapper.updateReward(vo);
	}

	@Override
	public int deleteReward(int rwrdNum) {
		// TODO Auto-generated method stub
		return mapper.deleteReward(rwrdNum);
	}

	
	
}
