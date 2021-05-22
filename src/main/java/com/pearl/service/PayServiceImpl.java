package com.pearl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearl.domain.PayDTO;
import com.pearl.mapper.PayMapper;

import lombok.Setter;

@Service
public class PayServiceImpl implements PayService{

	@Setter(onMethod_ = @Autowired)
	private PayMapper mapper;
	
	@Override
	public PayDTO get(Long payNum) {
		return null;
	}

	@Override
	public void insert(PayDTO dto) {
		mapper.insert(dto);
	}

	@Override
	public int delete(Long payNum) {
		// TODO Auto-generated method stub
		return 0;
	}

}
