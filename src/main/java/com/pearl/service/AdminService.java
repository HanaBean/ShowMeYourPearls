package com.pearl.service;

import java.util.List;

import com.pearl.domain.AdminBoardVO;
import com.pearl.domain.AdminFundVO;

public interface AdminService {
	List<AdminBoardVO> boardList();
	List<AdminFundVO> fundList();
	int fundDelete(Long arrFundNum[]);
	int memberDelete(Long arrMemNum[]);
	int postDelete(Long arrPostNum[]);
}
