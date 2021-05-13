package com.pearl.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@SequenceGenerator(
        name="MEMBER_SEQ",
        sequenceName="MEMBER_SEQ",
        initialValue=1,allocationSize=1)
@Table(name = "member")
public class MemberEntity {
	
	@Id
	@Column(name="memnum")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="MEMBER_SEQ")
    private Long memNum;

    @Column(name="mememail", length = 30, nullable = false)
    private String memEmail;
    
    @Column(name="mempass", length = 100, nullable = false)
    private String memPass;
    
    @Column(name="memname", length = 30, nullable = false)
    private String memName;
    
    @Column(name="memphone", length = 20, nullable = false)
    private String memPhone;
    
    @Column(name="memlevel", length = 10, nullable = false)
    private String memLevel;
    
    @Column(name="membank", length = 30)
    private String memBank;

    @Column(name="memaccount", length = 20)
    private String memAccount;
    
    @Builder
    public MemberEntity(Long memNum, String memEmail, String memPass, String memName, String memPhone, String memLevel) {
        this.memNum = memNum;
        this.memEmail = memEmail;
        this.memPass = memPass;
        this.memName = memName;
        this.memPhone = memPhone;
        this.memLevel = memLevel;
    }
}
