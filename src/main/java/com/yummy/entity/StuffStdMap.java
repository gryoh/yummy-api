package com.yummy.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "STUFF_STD_MAP")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StuffStdMap extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUFF_NO")
    private Long stuffNo;

    @OneToOne
    @JoinColumn(name = "stuffStdMap")
    private StuffBase stuffBase;
}
