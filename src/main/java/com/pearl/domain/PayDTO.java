package com.pearl.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PayDTO {
	private int payTotal, payCardNum, payCvc, payCardExpire, payDona;
	private Long fundNum, memNum, payNum; 
	private String payAdress;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date payTime;
}
