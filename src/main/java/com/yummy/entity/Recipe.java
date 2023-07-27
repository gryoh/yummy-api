package com.yummy.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "RCP_BASE")
public class Recipe extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RCP_NO")
    private Double rcpNo;

    @OneToMany
    @JoinColumn(name = "RCP_NO")
    private List<MbrRcpLike> mbrRcpLikes;

    @OneToMany
    @JoinColumn(name = "RCP_NO")
    private List<RcpStuff> rcpStuffs;


    @Column(name = "RCP_NAME")
    private Double rcpName;

    @Column(name = "RCP_DESCRIPTION")
    private Double rcpDescription;

}
