package com.pearl.domain;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BoardVO {
	private Long boardNum;
	private Long memNum;
	private String boardTitle;
	private String boardContent;
	private Date boardDate;
	private String boardType;
}
