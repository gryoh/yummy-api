package com.yummy.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ST_STD_CD")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StStdCd extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "GRP_CD")    private String grpCd;
	@Column(name = "CD")        private String cd;
	@Column(name = "USE_YN")    private String useYn;
}
