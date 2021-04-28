package com.pearl.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	private Long boardNum,memNum;
	private String boardTitle, boardContent;
	private Date boardDate;
	private char boardType;
}