package com.pearl.domain;

import lombok.Data;

@Data
public class MemberVO {
	private Long memNum;
	private String memName, memPass, memEmail;
	private Long memPhone;
	private String memLevel,memBank;
	private Long memAccount;
}