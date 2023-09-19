package com.yummy.dto;

import com.yummy.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
@Getter
@Setter
public class LoginLogDto extends BaseEntity {
    private Long mbrNo;
    private String lgoinIp;
    private String loginId;
    private String loginYn;
    private String resultCd;

}
