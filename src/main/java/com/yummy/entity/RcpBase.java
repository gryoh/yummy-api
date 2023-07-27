package com.yummy.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "RCP_BASE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RcpBase extends com.yummy.entity.BaseEntity {
    @Id @GeneratedValue
    @Column(name = "RCP_NO")            private Long rcpNo; //레시피번호
    @Column(name = "RCP_NAME")          private String rcpName; //레시피명
	@Column(name = "RCP_DESCRIPTION")   private String rcpDescription;  //레시피설명
	@Column(name = "USE_YN")            private String useYn;   //사용여부

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "stuff_no")
    private com.yummy.entity.RcpStuff rcpStuff;

    @OneToMany(mappedBy = "rcpBase")
    private List<com.yummy.entity.MbrRcpLike> mbrRcpLikes = new ArrayList<>();

    @OneToMany(mappedBy = "rcpBase")
    private List<com.yummy.entity.RcpStuff> rcpStuffs = new ArrayList<>();

    @OneToMany(mappedBy = "mealPlanner", cascade = CascadeType.ALL)
    private List<com.yummy.entity.MealPlannerMapping> mealPlannerMappings = new ArrayList<>();

    public RcpBase(String rcpName, String rcpDescription, String useYn) {
        this.rcpName = rcpName;
        this.rcpDescription = rcpDescription;
        this.useYn = useYn;
    }
}
