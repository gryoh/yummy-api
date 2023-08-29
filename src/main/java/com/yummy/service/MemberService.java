package com.yummy.service;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
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
import static org.springframework.util.StringUtils.isEmpty;

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

    /** 아이디 중복확인 **/
    public List<MbrBase> searchMember(MbrBaseDto condition) {
        return queryFactory
                .selectFrom(mbrBase)
                .where(
                        mbrBaseLoginIdEq(condition.getLoginId())
                )
                .fetch();
    }

    /** 로그안 **/
    public MbrBase loginMember(MbrBaseDto condition) {
        return queryFactory
                .selectFrom(mbrBase)
                .where(
                        mbrBaseLoginIdEq(condition.getLoginId())
                        , mbrBaseMbrPwEq(condition.getMbrPw())
                )
                .fetchFirst();
    }

    /** 회원가입 **/
    public MbrBase memberJoin(MbrBaseDto mbrBaseDto) {
//        if (mbrBase.getMbrEmail())
        if (isEmpty(mbrBaseDto.getMbrEmail())) {
            mbrBaseDto.setMbrEmail(mbrBaseDto.getLoginId());
        }
        MbrBase newMbrBase = new MbrBase(mbrBaseDto.getLoginId(), mbrBaseDto.getName(), mbrBaseDto.getMbrPw(),
                mbrBaseDto.getMbrPhon(), mbrBaseDto.getMbrEmail(), "20230101", "1", "1", null, "N",  "DEV", "DEV");
        return memberRepository.save(newMbrBase);

    }

    /**
     * mbrNo null check 값 비교
     * @param mbrNo
     * @return BooleanExpression
     */
    private BooleanExpression mbrBasemMbrNodEq(Long mbrNo) {
        return isEmpty(mbrNo) ? null : mbrBase.mbrNo.eq(mbrNo);
    }

    /**
     * mbrPw null check 값 비교
     * @param mbrPw
     * @return BooleanExpression
     */
    private BooleanExpression mbrBaseMbrPwEq(String mbrPw) {
        return isEmpty(mbrPw) ? null : mbrBase.mbrPw.eq(mbrPw);
    }

    /**
     * loginId null check 값 비교
     * @param loginId
     * @return BooleanExpression
     */
    private BooleanExpression mbrBaseLoginIdEq(String loginId) {
        return isEmpty(loginId) ? null : mbrBase.loginId.eq(loginId);
    }
}
