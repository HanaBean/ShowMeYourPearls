package com.pearl.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class MemberVO {
	
	private Long memNum;
	
	private String memName, memPass, memEmail,memPhone;
	private String memLevel,memBank, memAccount;
	
	public MemberEntity toEntity() {
		return MemberEntity.builder()
				.memNum(memNum)
				.memEmail(memEmail)
				.memPass(memPass)
				.memName(memName)
				.memPhone(memPhone)
				.memLevel("member")
				.build();
	}
	
	@Builder
	public MemberVO(Long memNum, String memEmail, String memPass, String memName, String memPhone) {
		this.memNum = memNum;
        this.memEmail = memEmail;
        this.memPass = memPass;
        this.memName = memName;
        this.memPhone = memPhone;
	}
}