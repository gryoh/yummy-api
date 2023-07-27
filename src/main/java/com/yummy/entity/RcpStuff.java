package com.yummy.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "RCP_STUFF")
public class RcpStuff extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RCP_STUFF_NO")
    private Double rcpStuffNo;

    @ManyToOne
    @JoinColumn(name = "STUFF_NO")
    private Stuff stuff ;

    @ManyToOne
    @JoinColumn(name = "RCP_NO")
    private Recipe recipe;

    @Column(name = "AMOUT")
    private Integer amout;

    @Column(name = "UNIT_CD")
    private Integer unitCd;
}
