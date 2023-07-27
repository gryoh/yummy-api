package com.yummy.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "MBR_STUFF")
public class MbrStuff extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MBR_STUFF_NO")
    private Double mbrNo;

    @ManyToOne
    @JoinColumn(name = "MBR_NO")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "STUFF_NO")
    private Stuff stuff;

    @Column(name = "AMOUT")
    private Integer amout;

    @Column(name = "UNIT_CD")
    private Integer unitCd;

    @Column(name = "EXPIRED_DATE")
    private LocalDateTime expiredDate;

}

