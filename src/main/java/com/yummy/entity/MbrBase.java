package com.yummy.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MBR_BASE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MbrBase extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MBR_NO")        private Long mbrNo;            // 회원번호
    @Column(name = "LOGIN_ID")      private String loginId;        // 로그인ID
    @Column(name = "NAME")          private String name;           // 회원명
    @Column(name = "MBR_PW")        private String mbrPw;          // 회원 비밀번호
    @Column(name = "MBR_PHON")      private String mbrPhon;        // 휴대폰 번호
    @Column(name = "MBR_EMAIL")     private String mbrEmail;       // 회원 이메일
    @Column(name = "MBR_BIRTH")     private String mbrBirth;       // 회원생년월일
    @Column(name = "MBR_GRADE_CD")  private String mbrGradeCd;     // 회원등급코드 10 일반회원 20 ... 30...
    @Column(name = "JOIN_CD")       private String joinCd;         // 가입 구분코드(1: 이메일가입, 2: 카카오가입, 3:네이버가입)
    @Column(name = "CNCL_DT")       private LocalDateTime cnclDt;  // 회원탈퇴일
    @Column(name = "CNCL_CD")       private String cnclCd;         // 회원탈퇴여부

    @OneToMany(mappedBy = "mbrBase", cascade = CascadeType.ALL)
    List<MbrRcpLike> mbrRcpLikes = new ArrayList<>();

    @OneToMany(mappedBy = "mbrBase", cascade = CascadeType.ALL)
    List<MbrStuff> mbrStuffs = new ArrayList<>();

    public MbrBase(String loginId, String name, String mbrPw, String mbrPhon, String mbrEmail, String mbrBirth, String mbrGradeCd, String joinCd, LocalDateTime cnclDt, String cnclCd) {
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

    public MbrBase(String loginId, String name, String mbrPw, String mbrPhon, String mbrEmail, String mbrBirth, String mbrGradeCd, String joinCd, LocalDateTime cnclDt, String cnclCd, LocalDateTime sysRegTime, String sysRegId, LocalDateTime sysModTime, String sysModId) {
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
        this.sysRegTime = sysRegTime;
        this.sysRegId = sysRegId;
        this.sysModTime = sysModTime;
        this.sysModId = sysModId;
    }
}
