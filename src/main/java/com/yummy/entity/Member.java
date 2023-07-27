package com.yummy.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "MBR_BASE")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MBR_NO")
    private Double mbrNo;

    @OneToMany
    @JoinColumn(name = "MBR_NO")
    private List<MbrStuff> mbrStuffs;

    @OneToMany
    @JoinColumn(name = "MBR_NO")
    private List<MbrRcpLike> mbrRcpLikes;

    @Column(name = "LOGIN_ID")
    private String loginId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MBR_PW")
    private String mbrPw;

    @Column(name = "MBR_PHONE")
    private String mbrPhone;

    @Column(name = "MBR_EMAIL")
    private String mbrEmail;

    @Column(name = "MBR_BIRTH")
    private String mbrBirth;

    @Column(name = "MBR_GRADE_CD")
    private String mbrGradeCd;

    @Column(name = "JOIN_CD")
    private String joinCd;

    @Column(name = "CNCL_DT")
    private String cnclDt;

    @Column(name = "CNCL_CD")
    private String cnclCd;



}
