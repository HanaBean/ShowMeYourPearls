package com.pearl.service;

import java.util.List;

import com.pearl.domain.FundVO;

public interface FundService {
	List<FundVO> getList();
	FundVO get(int fundNum);
	int insert(FundVO vo);
	int update(FundVO vo);
	int delete(int fundNum);
}
