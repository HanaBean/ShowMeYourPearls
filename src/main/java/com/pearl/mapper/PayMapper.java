package com.pearl.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pearl.domain.PayDTO;

@Mapper
public interface PayMapper {
	PayDTO get(Long payNum);
	void insert(PayDTO dto);
	int delete(Long payNum);
}
