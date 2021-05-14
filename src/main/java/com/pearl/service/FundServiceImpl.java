package com.pearl.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pearl.common.FileUtils;
import com.pearl.domain.FundVO;
import com.pearl.domain.PicDTO;
import com.pearl.domain.RewardVO;
import com.pearl.mapper.FundMapper;
import com.pearl.mapper.RewardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FundServiceImpl implements FundService{
	
//	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FileUtils fileUtils;
	
	@Setter(onMethod_ = @Autowired)
	private FundMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private RewardMapper rwMapper;

	@Override
	public List<FundVO> getList() {
		System.out.print(">>>" + mapper.getList().size()+"\n");
		// TODO Auto-generated method stub
		return mapper.getList();
	}

	@Override
	public FundVO get(Long fundNum){
		FundVO vo = mapper.get(fundNum);
		List<PicDTO> picList = mapper.getPicList(fundNum);
		vo.setPicList(picList);
		return vo;
	}
	
	@Transactional
	@Override
	public void insert(FundVO vo, ArrayList<RewardVO> itemList, MultipartHttpServletRequest mt) throws Exception {	
		mapper.insert(vo);
		
		for(int i=0;i<itemList.size();i++) {
			RewardVO item = itemList.get(i);
			item.setFundNum(vo.getFundNum());
			rwMapper.insertReward(item);
		}
		
		List<PicDTO> list = fileUtils.parseFileInfo(vo.getFundNum(), mt);
		
		log.info(">>>>>list"+ list);
		if(CollectionUtils.isEmpty(list)==false) {
			mapper.insertPic(list);
		}


		
	}

	@Override
	public int update(FundVO vo) {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Override
	public int delete(Long fundNum) {
		// TODO Auto-generated method stub
		return mapper.delete(fundNum);
	}
	
}
