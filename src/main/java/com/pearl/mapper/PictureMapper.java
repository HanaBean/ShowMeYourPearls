package com.pearl.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.pearl.domain.PictureVO;

@Mapper
public interface PictureMapper {
	
	public int insertPic(PictureVO picture);
	public PictureVO getPic(Long boardNum);
}
