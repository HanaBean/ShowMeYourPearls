package com.pearl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pearl.domain.FundVO;

public interface FundService {
	List<FundVO> getList();
	FundVO get(int fundNum);
	int insert(FundVO vo, MultipartHttpServletRequest mt) throws Exception;
	int update(FundVO vo);
	int delete(int fundNum);
}
