package com.yummy.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "LOGIN_LOG")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginLog extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MBR_NO")    private Long mbrNo;
    @Column(name = "LGOIN_IP")  private String lgoinIp;
    @Column(name = "LOGIN_ID")  private String loginId;
    @Column(name = "LOGIN_YN")  private String loginYn;
    @Column(name = "RESULT_CD") private String resultCd;
}
