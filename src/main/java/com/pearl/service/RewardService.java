package com.pearl.service;

import java.util.List;

import com.pearl.domain.FundVO;
import com.pearl.domain.RewardVO;

public interface RewardService {
	List<RewardVO> getListReward();
	int insertReward(RewardVO vo);
	int updateReward(RewardVO vo);
	int deleteReward(int rwrdNum);
}
