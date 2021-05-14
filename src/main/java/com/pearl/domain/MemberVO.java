package com.pearl.domain;

import lombok.Data;

@Data
public class MemberVO{
	
	private Long memNum;
	
	private String memName, memPass, memEmail,memPhone;
	private String memLevel,memBank, memAccount;
	
}