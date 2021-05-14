package com.pearl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pearl.domain.BoardVO;
import com.pearl.domain.MyPageVO;

@Mapper
public interface MyPageMapper {
	List<MyPageVO> myfundList(Long memNum);
	List<MyPageVO> donaList(Long memNum);
	List<BoardVO> mygallery(Long memNum);

}
