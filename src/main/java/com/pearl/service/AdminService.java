package com.pearl.service;

import java.util.List;

import com.pearl.domain.AdminBoardVO;
import com.pearl.domain.AdminFundVO;
import com.pearl.domain.AdminPaymentVO;
import com.pearl.domain.SearchVO;

public interface AdminService {
	List<AdminBoardVO> boardList();
	List<AdminFundVO> fundList();
	List<AdminPaymentVO> paymentList();
	List<AdminPaymentVO> searchPaymentList(SearchVO vo);
	int fundDelete(Long arrFundNum[]);
	int memberDelete(Long arrMemNum[]);
	int postDelete(Long arrBoardNum[]);
}
