package com.pearl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pearl.domain.MemberVO;
import com.pearl.domain.ReplyVO;

@Mapper
public interface ReplyMapper {
	List<ReplyVO> list();
	ReplyVO get(Long memNum);
	int insert(ReplyVO vo);
	int update(ReplyVO vo);
	int delete(int memNum);
}
