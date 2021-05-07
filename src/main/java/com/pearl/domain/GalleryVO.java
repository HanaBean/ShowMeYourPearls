package com.pearl.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class GalleryVO {
	private Long boardNum;
	private Long memNum;
	private String boardTitle;
	private String boardContent;
	private Date boardDate;
	private String boardType, memName;
}
