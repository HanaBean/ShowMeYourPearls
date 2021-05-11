package com.pearl.service;

import java.util.List;

import com.pearl.domain.AdminBoardVO;
import com.pearl.domain.BoardVO;
import com.pearl.domain.FundVO;
import com.pearl.domain.GalleryVO;

public interface MainService {
	List<GalleryVO> list();
	List<FundVO> fundlist();
	List<AdminBoardVO> payget();
	FundVO get(int fundNum);
	GalleryVO galleryget(Long boardNum);
}
