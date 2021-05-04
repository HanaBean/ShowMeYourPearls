package com.pearl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearl.domain.MemberVO;
import com.pearl.mapper.MemberMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MemberServiceImpl implements MemberService {
	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;
	
	@Override
	public List<MemberVO> list() {
		return mapper.list();
	}

	@Override
	public String get(MemberVO vo) {
		return mapper.get(vo);
	}

	@Override
	public int insert(MemberVO vo) {
		return mapper.insert(vo);
	}

	@Override
	public int update(MemberVO vo) {
		return mapper.update(vo);
	}

	@Override
	public int updateLevel(MemberVO vo) {
		return mapper.updateLevel(vo);
	}

	@Override
	public int delete(int memNum) {
		return mapper.delete(memNum);
	}

}
