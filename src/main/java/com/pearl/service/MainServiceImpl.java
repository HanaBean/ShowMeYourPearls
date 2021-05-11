package com.pearl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearl.domain.AdminBoardVO;
import com.pearl.domain.BoardVO;
import com.pearl.domain.FundVO;
import com.pearl.domain.GalleryVO;
import com.pearl.mapper.MainMapper;

import lombok.Setter;


@Service
public class MainServiceImpl implements MainService{
	
	@Setter(onMethod_ = @Autowired)
	private MainMapper mapper;


	@Override
	public List<GalleryVO> list() {
		return mapper.list();
	}

	@Override
	public List<FundVO> fundlist() {
		return mapper.fundlist();
	}

	@Override
	public List<AdminBoardVO> payget() {
		return mapper.payget();
	}

	@Override
	public GalleryVO galleryget(Long boardNum) {
		return mapper.galleryget(boardNum);
	}


	@Override
	public FundVO get(int fundNum) {
		return mapper.get(fundNum);
	}
	
}
