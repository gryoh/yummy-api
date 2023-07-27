package com.yummy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "MEAL_PLANNER")
@NoArgsConstructor
public class MealPlanner {

    @Id @GeneratedValue
    private Long mpNo ; //식단번호

    @Column(name = "MP_NAME")           private String mpName;  //식단명
    @Column(name = "MP_DESCRIPTION")    private String mpDescription;   //식단설명
    @Column(name = "MBR_NO")            private Long mbrNo;   //회원번호
    @Column(name = "DEL_YN")            private String delYn;   //삭제여부
    
    public MealPlanner(String mpName, String mpDescription, Long mbrNo, String delYn) {
        this.mpName = mpName;
        this.mpDescription = mpDescription;
        this.mbrNo = mbrNo;
        this.delYn = delYn;
    }

    @OneToMany(mappedBy = "mealPlanner", cascade = CascadeType.ALL)
    List<com.yummy.entity.MealPlannerMapping> mealPlannerMappings = new ArrayList<>();
}
