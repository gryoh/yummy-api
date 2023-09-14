package com.yummy.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import com.yummy.entity.RcpBase;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MealPlannerDto {
    private Long mpNo ; //식단번호
    private String mpName;  //식단명
    private String mpDescription;   //식단설명
    private Long mbrNo;   //회원번호
    private String delYn;   //삭제여부

    private Long rcpNo; //레시피번호
    private String rcpName;
    private String rcpDescription;
    private String rcpNos;  //레시피번호묶음
    private List<RcpBaseDto> rcpBaseList;   //레시피정보 리스트

    @QueryProjection
    public MealPlannerDto(Long mpNo, String mpName, String mpDescription, Long mbrNo, String delYn) {
        this.mpNo = mpNo;
        this.mpName = mpName;
        this.mpDescription = mpDescription;
        this.mbrNo = mbrNo;
        this.delYn = delYn;
    }

    @QueryProjection
    public MealPlannerDto(Long mpNo, String mpName, String mpDescription, Long mbrNo, String delYn, Long rcpNo) {
        this.mpNo = mpNo;
        this.mpName = mpName;
        this.mpDescription = mpDescription;
        this.mbrNo = mbrNo;
        this.delYn = delYn;
        this.rcpNo = rcpNo;
    }


    @QueryProjection
    public MealPlannerDto(Long mpNo, String mpName, String mpDescription, Long mbrNo, String delYn, Long rcpNo, String rcpName, String rcpDescription) {
        this.mpNo = mpNo;
        this.mpName = mpName;
        this.mpDescription = mpDescription;
        this.mbrNo = mbrNo;
        this.delYn = delYn;
        this.rcpNo = rcpNo;
        this.rcpName = rcpName;
        this.rcpDescription = rcpDescription;
    }
}
