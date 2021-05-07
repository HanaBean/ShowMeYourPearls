package com.pearl.service;

import java.util.List;

import com.pearl.domain.FundVO;
import com.pearl.domain.RewardVO;

public interface RewardService {
	List<RewardVO> getList();
	int insert(RewardVO vo);
	int update(RewardVO vo);
	int delete(int rwrdNum);
}
