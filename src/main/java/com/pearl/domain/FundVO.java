package com.pearl.domain;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("fundVO")
public class FundVO extends CommonDTO{
	//펀드 게시물 번호
	private Long fundNum;
	//회원 번호
	private Long memNum;
	//회원 이름
	private String memName;
	//펀드 모금 시작 날짜
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date fundStartDate;
	//펀드 모금 끝 날짜
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date fundEndDate;
	//펀드 모금 금액, 상품 금액
	private int fundMoney, rwrdTotal;
	//펀드 전시&배송 일자
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date fundDday;
	//펀드 게시물 제목
	private String fundTitle;
	//펀드 게시물 내용
	private String fundIntro;
	//펀드 게시물 업로드 사진
	private PictureVO pic;
	//펀드 상품 총 합계, 참가자 인원
	private int total, attend;
	//펀드 상품 목록
	private List<RewardVO> rwvo;
	//펀드 현재 상태(예정, 진행, 마감)
	private String fundNow;
}
