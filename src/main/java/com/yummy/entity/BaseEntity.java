package com.yummy.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @Column(name = "SYS_REG_NM")
    private String sysRegNm;
    @Column(name = "SYS_REG_TIME")
    private LocalDateTime sysRegTime;
    @Column(name = "SYS_MODI_NM")
    private String sysModiNm;
    @Column(name = "SYS_MODI_TIME")
    private LocalDateTime sysModiTime;
}