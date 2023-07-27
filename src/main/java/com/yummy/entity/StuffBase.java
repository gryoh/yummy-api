package com.yummy.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "STUFF_BASE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StuffBase extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "STUFF_NO")		private Long stuffNo;
    @Column(name = "STUFF_NAME")	private String stuffName;
	@Column(name = "STUFF_DESC")	private String stuffDesc;
	@Column(name = "USE_YN")		private String useYn;
	@Column(name = "INFO_VAL1")		private String infoVal1;
	@Column(name = "INFO_VAL2")		private String infoVal2;
	@Column(name = "INFO_VAL3")		private String infoVal3;
	@Column(name = "IMG_PATH")		private String imgPath;
	@Column(name = "IMG_FILENAME")	private String imgFilename;

	@OneToMany(mappedBy = "stuffBase", cascade = CascadeType.ALL)
	private List<MbrStuff> mbrStuffs = new ArrayList<>();

	@OneToOne
	private com.yummy.entity.RcpStuff rcpStuff;

	@OneToOne
	private com.yummy.entity.StuffStdMap stuffStdMap;

	public StuffBase(String stuffName, String stuffDesc, String useYn, String infoVal1, String infoVal2, String infoVal3, String imgPath, String imgFilename) {
		this.stuffName = stuffName;
		this.stuffDesc = stuffDesc;
		this.useYn = useYn;
		this.infoVal1 = infoVal1;
		this.infoVal2 = infoVal2;
		this.infoVal3 = infoVal3;
		this.imgPath = imgPath;
		this.imgFilename = imgFilename;
	}
}
