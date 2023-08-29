package com.yummy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
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

    /**
     * 회원가입 아이디 체크
     * @param mbrBaseDto
     * @return
     * @throws Exception
     */
    @PostMapping("/checkMemberId")
    @ResponseBody
    public boolean checkMemberId(@RequestBody MbrBaseDto mbrBaseDto) throws Exception {
        boolean result = false;
        // request 체크
        if (null == mbrBaseDto) {
            result = false;
        } else {
            //아이디 공백체크
            if (!isEmpty(mbrBaseDto.getLoginId())) {
                List<MbrBase> checkMemberIdList = memberService.searchMember(mbrBaseDto);
                if (null != checkMemberIdList && checkMemberIdList.size() > 0 ) {
                    result = false;
                } else {
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * 회원가입
     * @param mbrBaseDto
     * @return
     * @throws Exception
     */
    @PostMapping("/memberJoin")
    @ResponseBody
    @Transactional
    public MbrBase memberJoin(@RequestBody MbrBaseDto mbrBaseDto) throws Exception {
        MbrBase result = null;
        if (null != mbrBaseDto) {
            if (!isEmpty(mbrBaseDto.getName()) && !isEmpty(mbrBaseDto.getMbrPhon()) && !isEmpty(mbrBaseDto.getMbrPw()) && !isEmpty(mbrBaseDto.getMbrPhon()) && !isEmpty(mbrBaseDto.getMbrPw())) {
                result =  memberService.memberJoin(mbrBaseDto);
            }
        }
        return result;
    }

    /**
     * 로그인
     * @param mbrBaseDto
     * @return
     * @throws Exception
     */
    @PostMapping("/goLogin")
    @ResponseBody
    @Transactional
    public String login(@RequestBody MbrBaseDto mbrBaseDto) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String result;

        // request 체크
        if (null == mbrBaseDto) {
            result = "F";
        } else {
            //아이디 공백체크
            if (!isEmpty(mbrBaseDto.getLoginId()) && !isEmpty(mbrBaseDto.getMbrPw())) {
                MbrBase mbrBase = memberService.loginMember(mbrBaseDto);
                result = mapper.registerModule(new JavaTimeModule()).writeValueAsString(mbrBase);
            } else {
                result = "F";
            }
        }

        return result;
    }

}
