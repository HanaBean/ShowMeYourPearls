package com.pearl.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearl.domain.AdminBoardVO;
import com.pearl.domain.AdminFundVO;
import com.pearl.domain.AdminPaymentVO;
import com.pearl.domain.MemberVO;
import com.pearl.domain.SearchVO;
import com.pearl.mapper.AdminMapper;

import lombok.Setter;

@Service
public class AdminServiceImpl implements AdminService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Setter(onMethod_ = @Autowired)
	private AdminMapper mapper;

	@Override
	public List<MemberVO> test(MemberVO vo) {
		List<MemberVO> test = Collections.emptyList();
		return mapper.test(vo);
	}

	@Override
	public List<AdminBoardVO> boardList() {
		return mapper.boardList();
	}

	@Override
	public List<AdminFundVO> fundList() {
		return mapper.fundList();
	}
	
	@Override
	public List<AdminPaymentVO> paymentList() {
		return mapper.paymentList();
	}
	
	@Override
	public List<AdminPaymentVO> searchPaymentList(SearchVO vo) {
		return mapper.searchPaymentList(vo);
	}

	@Override
	public int fundDelete(Long[] arrFundNum) {
		return mapper.fundDelete(arrFundNum);
	}

	@Override
	public int memberDelete(Long[] arrMemNum) {
		return mapper.memberDelete(arrMemNum);
	}

	@Override
	public int postDelete(Long[] arrBoardNum) {
		return mapper.postDelete(arrBoardNum);
	}


}
