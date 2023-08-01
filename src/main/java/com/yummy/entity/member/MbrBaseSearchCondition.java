package com.yummy.entity.member;

import lombok.Data;

import javax.persistence.Column;

@Data
public class MbrBaseSearchCondition {
    private Long mbrNo;            // 회원번호
    private String loginId;        // 로그인ID
    private String mbrPw;          // 회원 비밀번호
}
