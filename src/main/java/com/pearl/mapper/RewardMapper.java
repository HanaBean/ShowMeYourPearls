package com.pearl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pearl.domain.FundVO;
import com.pearl.domain.RewardVO;

@Mapper
public interface RewardMapper {
	List<RewardVO> getListReward();
	int insertReward(RewardVO rwVo);
	int updateReward(RewardVO rwVo);
	int deleteReward(int rwrdNum);
}
