package com.yummy.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.yummy.entity.RcpStuff;
import lombok.Data;

import java.util.List;

@Data
public class RecipeDto {
    private Long rcpNo; //레시피번호
    private String rcpName; //레시피명
    private String rcpDescription;  //레시피설명
    private String useYn;   //사용여부
    private String filePath;    // 이미지경로
    private String fileName;    // 이미지명

    private List<RcpStuff> rcpStuffList;   //레시피재료 리스트

    @QueryProjection
    public RecipeDto(Long rcpNo, String rcpName, String rcpDescription, String useYn, String filePath, String fileName, List<RcpStuff> rcpStuffList) {
        this.rcpNo = rcpNo;
        this.rcpName = rcpName;
        this.rcpDescription = rcpDescription;
        this.useYn = useYn;
        this.filePath = filePath;
        this.fileName = fileName;
        this.rcpStuffList = rcpStuffList;
    }

    @QueryProjection
    public RecipeDto(Long rcpNo, String rcpName, String rcpDescription, String useYn, String filePath, String fileName) {
        this.rcpNo = rcpNo;
        this.rcpName = rcpName;
        this.rcpDescription = rcpDescription;
        this.useYn = useYn;
        this.filePath = filePath;
        this.fileName = fileName;
    }
}
