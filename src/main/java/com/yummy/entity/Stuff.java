package com.yummy.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "STUFF_BASE")
public class Stuff extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STUFF_NO")
    private Double stuffNo;

    @OneToMany
    @JoinColumn(name = "STUFF_NO")
    private List<MbrStuff> mbrStuffs;

    @OneToMany
    @JoinColumn(name = "STUFF_NO")
    private List<RcpStuff> rcpStuffs;


    @Column(name = "STUFF_NAME")
    private Double stuffName;

    @Column(name = "STUFF_DESC")
    private Double stuffDesc;

    @Column(name = "USE_YN")
    private Double useYn;

    @Column(name = "INFO_VAL1")
    private Double infoVal1;

    @Column(name = "INFO_VAL2")
    private Double infoVal2;

    @Column(name = "INFO_VAL3")
    private Double infoVal3;

    @Column(name = "IMG_PATH")
    private Double imgPath;

    @Column(name = "IMG_FILENAME")
    private Double imgFilename;


}
