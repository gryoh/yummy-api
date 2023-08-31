package com.yummy.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import com.yummy.entity.BaseEntity;
import com.yummy.entity.MbrBase;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Data
public class MbrBaseDto extends BaseEntity {

    private Long mbrNo;            // 회원번호
    private String loginId;        // 로그인ID
    private String name;           // 회원명
    private String mbrPw;          // 회원 비밀번호
    private String mbrPhon;        // 휴대폰 번호
    private String mbrEmail;       // 회원 이메일
    private String mbrBirth;       // 회원생년월일
    private String mbrGradeCd;     // 회원등급코드 10 일반회원 20 ... 30...
    private String joinCd;         // 가입 구분코드(1: 이메일가입, 2: 카카오가입, 3:네이버가입)
    private LocalDateTime cnclDt;  // 회원탈퇴일
    private String cnclCd;         // 회원탈퇴여부

    public MbrBaseDto(String loginId) {
        this.loginId = loginId;
    }

    @QueryProjection
    public MbrBaseDto(MbrBase mbrBase) {
        mbrNo = mbrBase.getMbrNo();
        loginId = mbrBase.getLoginId();
        name = mbrBase.getName();
        mbrPw = mbrBase.getMbrPw();
        mbrPhon = mbrBase.getMbrPhon();
        mbrEmail = mbrBase.getMbrEmail();
        mbrBirth = mbrBase.getMbrBirth();
        mbrGradeCd = mbrBase.getMbrGradeCd();
        joinCd = mbrBase.getJoinCd();
        cnclDt = mbrBase.getCnclDt();
        cnclCd = mbrBase.getCnclCd();
    }

    @QueryProjection
    public MbrBaseDto(Long mbrNo, String loginId, String name, String mbrPw, String mbrPhon, String mbrEmail, String mbrBirth, String mbrGradeCd, String joinCd, LocalDateTime cnclDt, String cnclCd) {
        this.mbrNo = mbrNo;
        this.loginId = loginId;
        this.name = name;
        this.mbrPw = mbrPw;
        this.mbrPhon = mbrPhon;
        this.mbrEmail = mbrEmail;
        this.mbrBirth = mbrBirth;
        this.mbrGradeCd = mbrGradeCd;
        this.joinCd = joinCd;
        this.cnclDt = cnclDt;
        this.cnclCd = cnclCd;
    }

    // 간단한 DTO
    @QueryProjection
    public MbrBaseDto(Long mbrNo, String loginId, String name, String mbrPhon, String mbrEmail, String mbrBirth, String mbrGradeCd) {
        this.mbrNo = mbrNo;
        this.loginId = loginId;
        this.name = name;
        this.mbrPhon = mbrPhon;
        this.mbrEmail = mbrEmail;
        this.mbrBirth = mbrBirth;
        this.mbrGradeCd = mbrGradeCd;
    }
}
