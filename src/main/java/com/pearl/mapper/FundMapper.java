package com.pearl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pearl.domain.FundVO;

@Mapper
public interface FundMapper {
	List<FundVO> getList();
	FundVO get(int fundNum);
	int insert(FundVO vo);
	int update(FundVO vo);
	int delete(int fundNum);
}
