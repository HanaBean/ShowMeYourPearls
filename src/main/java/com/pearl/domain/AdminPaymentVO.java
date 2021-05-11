package com.pearl.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class AdminPaymentVO {
	private Long payNum;
	private Long memNum;
	private Long fundNum;
	private Date payTime;
	private int payTotal;
	
}
