package com.pearl.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("fundVO")
public class FundVO {
	private int fundNum;
	private int memNum;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fundStartDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fundEndDate;
	private int fundMoney;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fundDday;
	private String fundTitle;
	private String fundIntro;
}
