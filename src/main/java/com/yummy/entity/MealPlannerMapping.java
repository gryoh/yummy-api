package com.yummy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "MEAL_PLANNER_MAPPING")
@NoArgsConstructor
public class MealPlannerMapping extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mpRcpNo;

    @ManyToOne(fetch = LAZY)
    private MealPlanner mealPlanner;

    @ManyToOne(fetch = LAZY)
    private RcpBase rcpBase;

    public MealPlannerMapping(Long mpNo, Long rcpNo) {
        if(this.mealPlanner == null){
            this.mealPlanner = new MealPlanner(mpNo);
        }
        if(this.rcpBase == null){
            this.rcpBase = new RcpBase(rcpNo);
        }
    }
}
