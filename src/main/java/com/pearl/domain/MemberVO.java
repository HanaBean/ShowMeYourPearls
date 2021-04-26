package com.pearl.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
	private int memNum;
	private String memName, memPass, memEmail;
	private int memPhone;
	private String memLevel,memBank;
	private int memAccount;
}