package com.pearl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearl.domain.FundVO;
import com.pearl.mapper.FundMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
public class FundServiceImpl implements FundService{
	
	@Setter(onMethod_ = @Autowired)
	private FundMapper mapper;

	@Override
	public List<FundVO> getList() {
		System.out.print(">>>" + mapper.getList().size()+"\n");
		// TODO Auto-generated method stub
		return mapper.getList();
	}

	@Override
	public FundVO get(int fundNum) {
		// TODO Auto-generated method stub
		return mapper.get(fundNum);
	}

	@Override
	public int insert(FundVO vo) {
		// TODO Auto-generated method stub
		return mapper.insert(vo);
	}

	@Override
	public int update(FundVO vo) {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Override
	public int delete(int fundNum) {
		// TODO Auto-generated method stub
		return mapper.delete(fundNum);
	}
	
}
