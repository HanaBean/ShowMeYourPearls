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
import com.pearl.domain.PicDTO;
import com.pearl.domain.PictureVO;
import com.pearl.mapper.FundMapper;
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
//		List<PicDTO> picList = mapper.getPicList(fundNum);
//		vo.setPicList(picList);
		return vo;
	}

	@Transactional
	@Override
	public void insert(FundVO vo) {
		mapper.insert(vo);
		/*
		 * for(int i=0;i<itemList.size();i++) { RewardVO item = itemList.get(i);
		 * item.setFundNum(vo.getFundNum()); rwMapper.insertReward(item); }
		 */

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
