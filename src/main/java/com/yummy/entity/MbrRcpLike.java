package com.yummy.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "MBR_RCP_LIKE")
public class MbrRcpLike extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MBR_RCP_NO")
    private Double mbrRcpNo;

    @ManyToOne
    @JoinColumn(name = "MBR_NO")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "RCP_NO")
    private Recipe recipe;
}
