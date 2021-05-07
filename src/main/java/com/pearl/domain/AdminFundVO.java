package com.pearl.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class AdminFundVO {
	private int fundNum;
	private int memNum;
	private String memName, memEmail;
	private Date fundStartDate;
	private Date fundEndDate;
	private int fundMoney;
	private String fundTitle;
	private String fundIntro;
}
