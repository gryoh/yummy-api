package com.yummy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yummy.dto.Const;
import com.yummy.dto.MbrBaseDto;
import com.yummy.entity.MbrBase;
import com.yummy.entity.RcpBase;
import com.yummy.entity.StuffBase;
import com.yummy.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.StringUtils.isEmpty;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mypage")
public class MyPageController {

    @Autowired
    MyPageService myPageService;

    /**
     * 회원 정보 조회
     * @param mbrBaseDto
     * @return
     * @throws Exception
     */
    @GetMapping("/getMyPageMbrRcpLike")
    @ResponseBody
    @Transactional
    public String getMyPageMbrRcpLike(Authentication authentication) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;

        // request 체크
        if (!isEmpty(authentication.getName())) {
            MbrBaseDto mbrBaseDto = new MbrBaseDto(authentication.getName());
            List<RcpBase> myPageMbrRcpLike = myPageService.getMyPageMbrRcpLike(mbrBaseDto);
            if (!ObjectUtils.isEmpty(myPageMbrRcpLike)) {
                result = mapper.registerModule(new JavaTimeModule()).writeValueAsString(myPageMbrRcpLike);
            }

        }

        return result;
    }

    /**
     * 회원 재료 조회
     * @param mbrBaseDto
     * @return
     * @throws Exception
     */
    @GetMapping("/getMyPageMbrStuff")
    @ResponseBody
    @Transactional
    public String getMyPageMbrStuff(Authentication authentication) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;

        // request 체크
        if (!isEmpty(authentication.getName())) {
            MbrBaseDto mbrBaseDto = new MbrBaseDto(authentication.getName());
            List<StuffBase> myPageMbrStuff = myPageService.getMyPageMbrStuff(mbrBaseDto);
            if (!ObjectUtils.isEmpty(myPageMbrStuff)) {
                result = mapper.registerModule(new JavaTimeModule()).writeValueAsString(myPageMbrStuff);
            }
        }
        return result;
    }

    /**
     * 회원 찜한 레시피, 재료 조회
     * @param mbrBaseDto
     * @return
     * @throws Exception
     */
    @GetMapping("/getMyPageRcpStuff")
    @ResponseBody
    @Transactional
    public String getMyPageRcpStuff(Authentication authentication) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;

        // request 체크
        if (!isEmpty(authentication.getName())) {
            MbrBaseDto mbrBaseDto = new MbrBaseDto(authentication.getName());
            List<StuffBase> myPageMbrStuff = myPageService.getMyPageMbrStuff(mbrBaseDto);
            List<RcpBase> myPageMbrRcpLike = myPageService.getMyPageMbrRcpLike(mbrBaseDto);
            Map<String, List<Object>> myPageRcpStuff = new HashMap<>();
            if (!ObjectUtils.isEmpty(myPageMbrStuff)) {
                myPageRcpStuff.put("myPageMbrStuff", Collections.singletonList(myPageMbrStuff));
            }
            if (!ObjectUtils.isEmpty(myPageMbrRcpLike)) {
                myPageRcpStuff.put("myPageMbrRcpLike", Collections.singletonList(myPageMbrRcpLike));
            }
            if (!ObjectUtils.isEmpty(myPageRcpStuff)) {
                result = mapper.registerModule(new JavaTimeModule()).writeValueAsString(myPageRcpStuff);
            }
        }
        return result;
    }
}
