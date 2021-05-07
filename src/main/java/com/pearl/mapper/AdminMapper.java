package com.pearl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pearl.domain.AdminBoardVO;
import com.pearl.domain.AdminFundVO;

@Mapper
public interface AdminMapper {
	List<AdminBoardVO> boardList();
	List<AdminFundVO> fundList();
	int fundDelete(Long arrFundNum[]);
	int memberDelete(Long arrMemNum[]);
	int postDelete(Long arrPostNum[]);
}
