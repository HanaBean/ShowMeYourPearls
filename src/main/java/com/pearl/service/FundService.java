package com.pearl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pearl.domain.FundVO;
import com.pearl.domain.RewardVO;

public interface FundService {
	List<FundVO> getList();
	FundVO get(Long fundNum);
	void insert(FundVO vo);
	int update(FundVO vo);
	int delete(Long fundNum);
}
