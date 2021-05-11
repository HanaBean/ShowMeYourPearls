package com.pearl.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pearl.domain.FundVO;
import com.pearl.mapper.FundMapper;

import lombok.Setter;

@Service
public class FundServiceImpl implements FundService{
	
	@Setter(onMethod_ = @Autowired)
	private FundMapper mapper;

	@Override
	public List<FundVO> getList() {
		System.out.print(">>>" + mapper.getList().size()+"\n");
		// TODO Auto-generated method stub
		return mapper.getList();
	}

	@Override
	public FundVO get(int fundNum) {
		// TODO Auto-generated method stub
		return mapper.get(fundNum);
	}

	@Override
	public int insert(FundVO vo, MultipartHttpServletRequest mt) throws Exception {
		//fundMapper.insert(vo);
		if(ObjectUtils.isEmpty(mt)== false) {
			Iterator<String> iterator = mt.getFileNames();
			String name;
			while(iterator.hasNext()) {
				name= iterator.next();
				System.out.println("file tag name : "+name);
				List<MultipartFile> picList = mt.getFiles(name);
				for(MultipartFile mtF : picList) {
					System.out.println("start file information");
					System.out.println("file name : " + mtF.getOriginalFilename() );
					System.out.println("file size : " + mtF.getSize());
					System.out.println("file content type : " + mtF.getContentType());
					System.out.println("end file information.\n");
				}
			}
		}
		return mapper.insert(vo);
	}

	@Override
	public int update(FundVO vo) {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Override
	public int delete(int fundNum) {
		// TODO Auto-generated method stub
		return mapper.delete(fundNum);
	}
	
}
