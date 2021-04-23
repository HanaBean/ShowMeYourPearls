create table member (
    mNum number,
    mName varchar2(30) not null,
    mPass varchar2(20) not null,
    mEmail varchar2(30) unique not null,
    mPhone number(11),
    mLevel varchar2(10) DEFAULT 'member' not null,
    mBank varchar2(30),
    mAccount number,
    constraint member_pk primary key(mnum),
    CONSTRAINT level_chk check(mlevel in('admin','funding','artist','member'))
);

create table board (
    bNum number,
    mNum number not null,
    bTitle varchar2(100) not null,
    bContent varchar2(3000) not null,
    bDate date not null,
    bType char(1) not null,
    CONSTRAINT board_pk primary key(bnum),
    CONSTRAINT board_fk foreign key(mnum) references member(mnum)
);

create table funding (
	fNum number,
	mNum number not null,
	fstartdate date not null,
	fenddate date not null,
	fmoney number not null,
	fdday date not null,
	ftitle varchar2(100) not null,
    fintro varchar2(500),
    constraint fund_pk primary key(fnum),
    constraint fund_fk foreign key(mnum) references member(mnum),
    constraint fdate_chk check(fenddate>=fstartdate and fdday>=fenddate and fenddate-fstartdate<=35)
);

create table reward (
    rewardNum number,
    rewardName varchar2(60) not null,
    fNum number not null,
    rewardPrice number not null,
    CONSTRAINT reward_pk primary key(rewardnum),
    CONSTRAINT reward_fk foreign key(fnum) references funding(fnum),
    constraint reward_price_chk check(rewardprice>=0)
);

create table Payment (
    pNum NUMBER,
    pTime date not null,
    fNum NUMBER not null,
    mNUM number not null,
    pCardNum NUMBER(16) not null,
    pcvc number(3) not null,
    pcardexpire number(4) not null,
    pdona number default 0,
    pTotal number not null,
    pAdress varchar2(300) not null,
    constraint payment_pk PRIMARY KEY(pnum),
    constraint payfnum_fk foreign key(fnum) references funding(fnum),
    constraint paymnum_fk foreign key(mnum) references member(mnum),
    constraint paydona_chk check(pdona>=0),
    constraint paytot_chk check(ptotal>=0)
);

create table item (
    itemNum number,
    rewardNum number not null,
    rewardCount number default 1 not null,
    pNum number not null,
    constraint item_pk primary key(itemnum),
    constraint item_fk foreign key(pnum) references payment(pnum)
);

create table reply(
    rNum number,
    mNum number not null,
    rContent varchar2(1000) not null,
    rDate date not null,
    bNum number not null,
    constraint reply_pk primary key(rnum),
    constraint rpl_mem_fk foreign key(mnum) references member(mnum),
    constraint rpl_board_fk foreign key(bnum) references board(bnum)
);

create table emotion(
	emonum number,
	bnum number not null,
	mnum number not null,
	emoexpress char(1) not null,
    constraint emo_pk primary key(emonum),
    constraint emo_member_fk foreign key(mnum) references member(mnum),
    constraint emo_board_fk foreign key(bnum) references board(bnum)
);

create table picture (
    picNum number,
    picUuid varchar2(300) not null,
    picPath varchar2(1000) not null,
    picName varchar2(500) not null,
    picTail varchar2(10) not null,
    picclass char(1) not null,
    postNum number not null,
    constraint pic_pk primary key(picnum)
);

create table subscribe (
    artist number not null,
    audience number not null,
    constraint subs_chk check(artist != audience)
);


create sequence member_seq;
create sequence board_seq;
create sequence funding_seq;
create sequence reply_seq;
create sequence emotion_seq;
create sequence reward_seq;
create sequence payment_seq;
create sequence item_seq;
create sequence picture_seq;

COMMENT ON TABLE MEMBER IS '회원';
COMMENT ON COLUMN MEMBER.mNum IS '회원 번호';
COMMENT ON COLUMN MEMBER.mname IS '회원 이름';
COMMENT ON COLUMN MEMBER.mpass IS '비밀번호';
COMMENT ON COLUMN MEMBER.memail IS '이메일 주소';
COMMENT ON COLUMN MEMBER.mphone IS '연락처';
COMMENT ON COLUMN MEMBER.mlevel IS '회원 등급';
COMMENT ON COLUMN MEMBER.mbank IS '은행 이름';
COMMENT ON COLUMN MEMBER.maccount IS '계좌 번호';

COMMENT ON TABLE BOARD IS '게시글';
COMMENT ON COLUMN BOARD.BNUM IS '게시글 번호';
COMMENT ON COLUMN BOARD.MNUM IS '작성자 번호';
COMMENT ON COLUMN BOARD.BTITLE IS '게시글 제목';
COMMENT ON COLUMN BOARD.BCONTENT IS '게시글 내용';
COMMENT ON COLUMN BOARD.BDATE IS '등록 날짜';
COMMENT ON COLUMN BOARD.BTYPE IS '게시글 분류';

COMMENT ON TABLE FUNDING IS '펀딩';
COMMENT ON COLUMN FUNDING.FNUM IS '펀딩 번호';
COMMENT ON COLUMN FUNDING.MNUM IS '작가 번호';
COMMENT ON COLUMN FUNDING.FSTARTDATE IS '펀딩 시작일';
COMMENT ON COLUMN FUNDING.FENDDATE IS '펀딩 종료일';
COMMENT ON COLUMN FUNDING.FMONEY IS '목표 금액';
COMMENT ON COLUMN FUNDING.FDDAY IS '전시일자';
COMMENT ON COLUMN FUNDING.FTITLE IS '펀딩 제목';
COMMENT ON COLUMN FUNDING.FINTRO IS '펀딩 소개글';

COMMENT ON TABLE REWARD IS '리워드';
COMMENT ON COLUMN REWARD.REWARDNUM IS '리워드 번호';
COMMENT ON COLUMN REWARD.REWARDNAME IS '리워드 이름';
COMMENT ON COLUMN REWARD.FNUM IS '펀딩 번호';
COMMENT ON COLUMN REWARD.REWARDPRICE IS '리워드 가격';

COMMENT ON TABLE PAYMENT IS '결제';
COMMENT ON COLUMN PAYMENT.pNum IS '결제 번호';
COMMENT ON COLUMN PAYMENT.pTime IS '결제 시간';
COMMENT ON COLUMN PAYMENT.fNum IS '펀딩 번호';
COMMENT ON COLUMN PAYMENT.mNUM IS '회원 번호';
COMMENT ON COLUMN PAYMENT.pCardNum IS '카드 번호';
COMMENT ON COLUMN PAYMENT.PCVC IS '카드 cvc';
COMMENT ON COLUMN PAYMENT.PCARDEXPIRE IS '카드 유효기간';
COMMENT ON COLUMN PAYMENT.PDONA IS '추가 후원금';
COMMENT ON COLUMN PAYMENT.pTotal IS '결재 총액';
COMMENT ON COLUMN PAYMENT.PADRESS IS '고객 주소';

COMMENT ON TABLE ITEM IS '결제품목';
COMMENT ON COLUMN ITEM.ITEMNUM IS '결제품목 번호';
COMMENT ON COLUMN ITEM.REWARDNUM IS '리워드 번호';
COMMENT ON COLUMN ITEM.REWARDCOUNT IS '수량';
COMMENT ON COLUMN ITEM.PNUM IS '결제 번호';

COMMENT ON TABLE REPLY IS '댓글';
COMMENT ON COLUMN REPLY.RNUM IS '댓글 번호';
COMMENT ON COLUMN REPLY.MNUM IS '댓글 작성자 번호';
COMMENT ON COLUMN REPLY.RCONTENT IS '댓글 내용';
COMMENT ON COLUMN REPLY.RDATE IS '작성 일자';
COMMENT ON COLUMN REPLY.BNUM IS '게시글 번호';

COMMENT ON TABLE EMOTION IS '감정표현';
COMMENT ON COLUMN EMOTION.EMONUM IS '감정표현 번호';
COMMENT ON COLUMN EMOTION.BNUM IS '게시글 번호';
COMMENT ON COLUMN EMOTION.MNUM IS '회원 번호';
COMMENT ON COLUMN EMOTION.EMOEXPRESS IS '선택한 감정';

COMMENT ON TABLE picture IS '사진';
COMMENT ON COLUMN picture.picNum is '사진 번호';
COMMENT ON COLUMN picture.picUuid is 'UUID';
COMMENT ON COLUMN picture.picPath is '파일 경로';
COMMENT ON COLUMN picture.picName is '파일 명';
COMMENT ON COLUMN picture.picTail is '파일 확장자';
COMMENT ON COLUMN picture.picclass is '글 종류';
COMMENT ON COLUMN picture.postNum is '글 번호';

