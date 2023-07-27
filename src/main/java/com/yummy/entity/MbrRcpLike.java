package com.yummy.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "MBR_RCP_LIKE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MbrRcpLike extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "MBR_RCP_NO")    private Long mbrRcpNo;
//    @Column(name = "MBR_NO")        private Long mbrNo;
//    @Column(name = "RCP_NO")        private Long rcpNo;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mbrNo")
    private com.yummy.entity.MbrBase mbrBase;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "rcpNo")
    private RcpBase rcpBase;
}
