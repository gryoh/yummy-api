package com.yummy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RcpBaseDto {
    private Long rcpNo; //레시피번호
    private String rcpName; //레시피명
    private String rcpDescription;  //레시피설명
    private String useYn;   //사용여부


}
