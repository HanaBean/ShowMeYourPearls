package com.pearl.domain;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BoardVO {
	private int bNum;
	private int mNum;
	private String bTitle;
	private String bContent;
	private Date bDate;
	private String bType;
}
