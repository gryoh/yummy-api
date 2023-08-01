package com.yummy.controller;

import com.yummy.entity.member.MbrBaseSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.yummy.dto.MbrBaseDto;
import com.yummy.entity.MbrBase;
import com.yummy.service.MemberService;

import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;

@RestController
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping(value = "/v1/insertMember")
    @ResponseBody
    public MbrBaseDto insertMember(@RequestBody @Validated MbrBase mbrBase){
        memberService.insertMember(mbrBase);
        return new MbrBaseDto(mbrBase);
    }

    // postman 테스트
    // http://localhost:8080/v1/mbrList?page=1&size=2
    // => 2개씩 나눠져있는 페이지 중 2번째 페이지내용 노출
    // page : page 번호(0부터 시작)
    // size : 한 페이지에 노출되는 갯수
    @GetMapping(value = "/v1/mbrList")
    public Page<MbrBaseDto> mbrList(@PageableDefault(size = 2, page = 1) Pageable pageable){
        return memberService.selectMbrList(pageable);
    }

    @GetMapping("/checkMemberId")
    @ResponseBody
    public String checkMemberId(@RequestBody MbrBaseSearchCondition mbrBaseSearchCondition) throws Exception {
        String result = "T";
        // request 체크
        if (null == mbrBaseSearchCondition) {
            result = "Bad Request";
        } else {
            //아이디 공백체크
            if (!isEmpty(mbrBaseSearchCondition.getLoginId())) {
                List<MbrBase> checkMemberIdList = memberService.searchMember(mbrBaseSearchCondition);
                if (null != checkMemberIdList && checkMemberIdList.size() > 0 ) {
                    result = "F";
                } else {
                    result = mbrBaseSearchCondition.getLoginId();
                }
            }
        }
        return result;
    }

    @GetMapping("/login")
    @ResponseBody
    public String login(@RequestBody MbrBaseSearchCondition mbrBaseSearchCondition) throws Exception {
        String result = "F";
        // request 체크
        if (null == mbrBaseSearchCondition) {
            result = "Bad Request";
        } else {
            //아이디 공백체크
            if (!isEmpty(mbrBaseSearchCondition.getLoginId()) && !isEmpty(mbrBaseSearchCondition.getMbrPw())) {
                List<MbrBase> checkMemberIdList = memberService.searchMember(mbrBaseSearchCondition);
                if (null != checkMemberIdList && checkMemberIdList.size() > 0 ) {
                    result = "로그인성공 ---> " + mbrBaseSearchCondition.getLoginId();
                } else {
                    result = "F";
                }
            }
        }
        return result;
    }

}
