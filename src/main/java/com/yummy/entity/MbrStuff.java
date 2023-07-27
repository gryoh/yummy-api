package com.yummy.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "MBR_STUFF")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MbrStuff extends com.yummy.entity.BaseEntity {
    @Id @GeneratedValue
    @Column(name = "RCP_STUFF_NO")  private String rcpStuffNo;
    @Column(name = "AMOUT")         private String amout;
    @Column(name = "UNIT_CD")       private String unitCd;
	@Column(name = "EXPIRED_DATE")  private LocalDateTime expiredDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mbr_no")
    private com.yummy.entity.MbrBase mbrBase;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "stuff_no")
    private com.yummy.entity.StuffBase stuffBase;
}
