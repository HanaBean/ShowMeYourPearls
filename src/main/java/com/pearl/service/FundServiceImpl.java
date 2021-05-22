package com.pearl.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pearl.common.FileUtils;
import com.pearl.domain.FundVO;
import com.pearl.domain.GalleryVO;
import com.pearl.domain.MemberVO;
import com.pearl.domain.PicDTO;
import com.pearl.domain.PictureVO;
import com.pearl.domain.RewardVO;
import com.pearl.mapper.FundMapper;
import com.pearl.mapper.MemberMapper;
import com.pearl.mapper.PictureMapper;
import com.pearl.mapper.RewardMapper;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FundServiceImpl implements FundService {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Setter(onMethod_ = @Autowired)
	private FundMapper mapper;

	@Setter(onMethod_ = @Autowired)
	private RewardMapper rwMapper;
	
	@Setter(onMethod_ = @Autowired)
	private PictureMapper picMapper;
	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper memMapper;

	@Override
	public List<FundVO> getList() {
		List<FundVO> list = mapper.getList();
		for(int i=0; i<list.size();i++) {
			FundVO fund = list.get(i);
			PictureVO pic = picMapper.getPicF(fund.getFundNum());
			fund.setPic(pic);
		}
		return list;
	}
	
	@Override
	public FundVO get(Long fundNum) {
		FundVO vo = mapper.get(fundNum);
		vo.setRwvo(rwMapper.getListReward(fundNum));
		vo.setPic(picMapper.getPicF(fundNum));
		return vo;
	}
	
	@Override
	public MemberVO artist(Long memNum) {
		MemberVO vo = memMapper.getProfile(memNum);
		vo.setProfile(picMapper.getProfile(memNum));
		return vo;
	}

	@Override
	public FundVO getPay(FundVO vo) {
		vo.setPic(picMapper.getPicF(vo.getFundNum()));
//		List<RewardVO> rwrd = rwMapper.getListReward(vo.getFundNum());
		List<RewardVO> fundRwrd = vo.getRwvo();
		for(int i=0;i<fundRwrd.size();i++) {
			String name = fundRwrd.get(i).getRwrdName();
			String price = name.substring(name.lastIndexOf("(")+1, name.lastIndexOf("원"));
			fundRwrd.get(i).setRwrdPrice(Integer.parseInt(price));
		}
//		for(int i=0;i<fundRwrd.size();i++) {
//			for(int j=0;j<rwrd.size();j++) {
//				if(fundRwrd.get(i).getRwrdNum()==rwrd.get(j).getRwrdNum()) {
//					fundRwrd.get(i).setRwrdPrice(rwrd.get(j).getRwrdPrice());
//				}
//			}
//		}
		vo.setRwvo(fundRwrd);
		return vo;
	}

	@Transactional
	@Override
	public void insert(FundVO vo) {
		mapper.insert(vo);
		List<RewardVO> rwrdList = vo.getRwvo();
		for(int i=0;i<rwrdList.size();i++) { 
			RewardVO rwrd = rwrdList.get(i);
			rwrd.setFundNum(vo.getFundNum()); 
			rwMapper.insertReward(rwrd); 
		}
		

		/*
		 * List<PicDTO> list = fileUtils.parseFileInfo(vo.getFundNum(), mt);
		 * 
		 * log.info(">>>>>list"+ list); if(CollectionUtils.isEmpty(list)==false) {
		 * mapper.insertPic(list);
		 */
		// }
		if(vo.getPic()==null) {
			return;
		}
		PictureVO picture = vo.getPic();
		log.info(">>>>>>>>PostNum:"+vo.getFundNum());
		String picPath = picture.getPicPath();
		log.info(">>>>>>>>picPath:"+picPath.split("\\\\")[0]+"%5C"+picPath.split("\\\\")[1]+"%5C"+picPath.split("\\\\")[2]);
		picture.setPicPath(
				picPath.split("\\\\")[0]+"%5C"+picPath.split("\\\\")[1]+"%5C"+picPath.split("\\\\")[2]);
		picture.setPostNum(vo.getFundNum());
		picture.setPicClass("f");
		picMapper.insertPic(picture);

	}

	@Override
	public int update(FundVO vo) {
		return mapper.update(vo);
	}

	@Override
	public int delete(Long fundNum) {
		return mapper.delete(fundNum);
	}

}
