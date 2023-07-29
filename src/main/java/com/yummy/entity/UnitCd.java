package com.yummy.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "UNIT_CD")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UnitCd extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UNIT_CD")   private Long id;
	@Column(name = "UNIT_NAME") private String unitName;
	@Column(name = "USE_YN")    private String useYn;
}
