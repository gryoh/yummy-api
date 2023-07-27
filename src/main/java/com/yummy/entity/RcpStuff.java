package com.yummy.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "RCP_STUFF")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RcpStuff extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "RCP_STUFF_NO")  private Long rcpStuffNo;
    @Column(name = "AMOUNT")        private String amount;
	@Column(name = "UNIT_CD")       private Integer unitCd;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "rcpNo")
    private RcpBase rcpBase;

    @OneToOne
    @JoinColumn(name = "stuffNo")
    private StuffBase stuffBase;


    public RcpStuff(String amount, Integer unitCd, RcpBase rcpBase, StuffBase stuffBase) {
        this.amount = amount;
        this.unitCd = unitCd;
        this.rcpBase = rcpBase;

        if(stuffBase != null){
            changeStuff(stuffBase);
        }
    }

    private void changeStuff(StuffBase stuffBase) {
        this.stuffBase = stuffBase;
        rcpBase.getRcpStuffs().add(this);
    }
}
