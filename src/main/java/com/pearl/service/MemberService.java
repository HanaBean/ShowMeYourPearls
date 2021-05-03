package com.pearl.service;

import java.util.List;

import com.pearl.domain.MemberVO;

public interface MemberService {
	List<MemberVO> list();
	String get(MemberVO vo);
	int insert(MemberVO vo);
	int update(MemberVO vo);
	int updateLevel(MemberVO vo);
	int delete(int memNum);

}
