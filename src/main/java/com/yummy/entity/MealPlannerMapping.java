package com.yummy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "MEAL_PLANNER_MAPPING")
@NoArgsConstructor
public class MealPlannerMapping extends com.yummy.entity.BaseEntity {

    @Id @GeneratedValue
    private Long mpRcpNo;

    @ManyToOne(fetch = LAZY)
    private MealPlanner mealPlanner;

    @ManyToOne(fetch = LAZY)
    private RcpBase rcpBase;
}
