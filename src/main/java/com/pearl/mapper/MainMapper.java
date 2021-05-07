package com.pearl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pearl.domain.EmotionVO;

@Mapper
public interface MainMapper {
	int emotionInsert(EmotionVO vo);
	EmotionVO getEmo(EmotionVO vo);
	int updateEmo(EmotionVO vo);
	List<EmotionVO> emoCount(int boardNum);
}
