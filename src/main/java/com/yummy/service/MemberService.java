package com.yummy.service;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yummy.dto.MbrBaseDto;
import com.yummy.dto.QMbrBaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.yummy.entity.MbrBase;
import com.yummy.repository.MemberRepository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.yummy.entity.QMbrBase.mbrBase;

@Service
public class MemberService {
    private final JPAQueryFactory queryFactory;
    public MemberService(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Autowired
    private MemberRepository memberRepository;

    public void insertMember(MbrBase mbrBase){
        memberRepository.save(mbrBase);
    }

    public Page<MbrBaseDto> selectMbrList(Pageable pageable) {
        QueryResults<MbrBaseDto> result = queryFactory.select(new QMbrBaseDto(
                        mbrBase.mbrNo,
                        mbrBase.loginId,
                        mbrBase.name,
                        mbrBase.mbrPhon,
                        mbrBase.mbrEmail,
                        mbrBase.mbrBirth,
                        mbrBase.mbrGradeCd
                ))
                .from(mbrBase)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MbrBaseDto> content = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

}
