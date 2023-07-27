package com.yummy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yummy.entity.MbrBase;
import com.yummy.repository.MemberRepository;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public void insertMember(MbrBase mbrBase){
        memberRepository.save(mbrBase);
    }

}
