package com.pearl.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearl.domain.BoardVO;
import com.pearl.domain.GalleryVO;
import com.pearl.domain.MemberVO;
import com.pearl.mapper.GalleryMapper;
import com.pearl.paging.Criteria;
import com.pearl.paging.PaginationInfo;

import lombok.Setter;

@Service
public class GalleryServiceImpl implements GalleryService{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Setter(onMethod_ = @Autowired)
	private GalleryMapper mapper;

	@Override
	public List<GalleryVO> list(GalleryVO vo) {
		List<GalleryVO> list = Collections.emptyList();
		int count = mapper.selectTotalCount(vo);
		log.info("list count:"+count);
		PaginationInfo pagiInfo = new PaginationInfo(vo);
		pagiInfo.setTotalCount(count);
		
		vo.setPagiInfo(pagiInfo);
		
		if(count>0) {
			list=mapper.list(vo);
		}
		return list;
	}

	@Override
	public BoardVO read(int boardNum) {
		return mapper.read(boardNum);
	}
	
	@Override
	public MemberVO readWriter(Long memNum) {
		return mapper.readWriter(memNum);
	}

	@Override
	public int insert(BoardVO vo) {
		return mapper.insert(vo);
	}

	@Override
	public int update(BoardVO vo) {
		return mapper.update(vo);
	}

	@Override
	public int delete(int boardNum) {
		return mapper.delete(boardNum);
	}

}
