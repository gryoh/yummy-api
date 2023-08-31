package com.yummy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yummy.dto.Const;
import com.yummy.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
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
                MbrBase mbrBase = memberService.searchMember(mbrBaseDto);
                if (ObjectUtils.isEmpty(mbrBase)) {
                    result = true;
                } else {
                    result = false;
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
    public String goLogin(@RequestBody MbrBaseDto mbrBaseDto) throws Exception {
        String result = Const.BOOLEAN_FALSE;;

        // request 체크
        if (null != mbrBaseDto) {
            if (!isEmpty(mbrBaseDto.getLoginId()) && !isEmpty(mbrBaseDto.getMbrPw())) {
                MbrBase mbrBase = memberService.searchMember(mbrBaseDto);
                // 로그인 성공시 토큰 발행
                if (!ObjectUtils.isEmpty(mbrBase)) {
                    String secretKey = "yummy-secret-key-20230831";
                    long expireTimeMs = 1000 * 60 * 60;
                    result = "Yummy " + JwtTokenUtil.createToken(mbrBase.getLoginId(), secretKey, expireTimeMs);
                }
            }
        }

        return result;
    }

    /**
     * 회원 정보 조회
     * @param mbrBaseDto
     * @return
     * @throws Exception
     */
    @GetMapping("/getMbrInfo")
    @ResponseBody
    @Transactional
    public String getMbrInfo(Authentication authentication) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String result = Const.BOOLEAN_FALSE;

        // request 체크
        if (!isEmpty(authentication.getName())) {
            MbrBaseDto mbrBaseDto = new MbrBaseDto(authentication.getName());
            MbrBase mbrBase = memberService.searchMember(mbrBaseDto);
            if (!ObjectUtils.isEmpty(mbrBase)) {
                result = mapper.registerModule(new JavaTimeModule()).writeValueAsString(mbrBase);
            }

        }

        return result;
    }

}
