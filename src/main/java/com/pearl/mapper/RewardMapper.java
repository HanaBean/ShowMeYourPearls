package com.pearl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pearl.domain.FundVO;
import com.pearl.domain.RewardVO;

@Mapper
public interface RewardMapper {
	List<RewardVO> getList();
	int insert(RewardVO vo);
	int update(RewardVO vo);
	int delete(int rwrdNum);
}
