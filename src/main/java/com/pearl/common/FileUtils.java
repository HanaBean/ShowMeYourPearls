package com.pearl.common;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pearl.domain.PicDTO;

@Component
public class FileUtils {
	public List<PicDTO> parseFileInfo(Long fundNum, MultipartHttpServletRequest mt) throws Exception {
		if (ObjectUtils.isEmpty(mt)) {
			return null;
		}
		List<PicDTO> fileList = new ArrayList<>();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
		String path = "images/" + current.format(format);
		File file = new File(path);
		if (file.exists() == false) {
			file.mkdirs();
		}
		Iterator<String> iterator = mt.getFileNames();
		String newFileName, originalFileExtension, contentType;

		while (iterator.hasNext()) {
			List<MultipartFile> list = mt.getFiles(iterator.next());
			for (MultipartFile mtF : list) {
				if (mtF.isEmpty() == false) {
					contentType = mtF.getContentType();
					if(ObjectUtils.isEmpty(contentType)) {
						break;
						
					}else {
						if(contentType.contains("image/jpeg")) {
							originalFileExtension = ".jpg";
						}
						if(contentType.contains("image/jpeg")) {
							originalFileExtension = ".png";
						}
						if(contentType.contains("image/jpeg")) {
							originalFileExtension = ".jpeg";
						}
						if(contentType.contains("image/jpeg")) {
							originalFileExtension = ".gif";
						}
						else {
							break;
						}
					}
					newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
					PicDTO pic = new PicDTO();
					pic.setFundNum(fundNum);
					pic.setFileSize(mtF.getSize());
					pic.setOriginalFileName(mtF.getOriginalFilename());
					pic.setStoredFilePath(path+"/" +newFileName);
					fileList.add(pic);
					
					file = new File(path+"/" + newFileName);
					mtF.transferTo(file);
					
				}
			}
		}
		return fileList;
	}
}
