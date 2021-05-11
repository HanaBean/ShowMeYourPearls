package com.pearl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pearl.domain.AdminBoardVO;
import com.pearl.domain.FundVO;
import com.pearl.domain.GalleryVO;

@Mapper
public interface MainMapper {
	List<GalleryVO> list(); 
	List<FundVO> fundlist();
	FundVO get(Long fundNum);
	GalleryVO galleryget(Long boardNum);
}
