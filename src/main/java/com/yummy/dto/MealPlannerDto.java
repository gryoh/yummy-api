package com.yummy.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Data;
import com.yummy.entity.RcpBase;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MealPlannerDto {
    private Long mpNo ; //식단번호
    private String mpName;  //식단명
    private String mpDescription;   //식단설명
    private Long mbrNo;   //회원번호
    private String delYn;   //삭제여부

    private String rcpNos;  //레시피번호묶음
    private List<RcpBase> rcpBaseList;   //레시피정보 리스트

    private Long rcpNo; //레시피번호
    private String rcpName; //레시피명
    private String rcpDescription;  //레시피설명
    private String useYn;   //사용여부
    private String filePath;    // 이미지경로
    private String fileName;    // 이미지명

    @QueryProjection
    public MealPlannerDto(Long mpNo, String mpDescription, Long rcpNo, String rcpName, String rcpDescription, String filePath, String fileName) {
        this.mpNo = mpNo;
        this.mpDescription = mpDescription;
        this.rcpNo = rcpNo;
        this.rcpName = rcpName;
        this.rcpDescription = rcpDescription;
        this.filePath = filePath;
        this.fileName = fileName;
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
}
