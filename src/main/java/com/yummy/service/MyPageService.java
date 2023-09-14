package com.yummy.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yummy.dto.MbrBaseDto;
import com.yummy.entity.MbrRcpLike;
import com.yummy.entity.QRcpBase;
import com.yummy.entity.RcpBase;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

import static com.yummy.entity.QMbrBase.mbrBase;
import static com.yummy.entity.QMbrRcpLike.mbrRcpLike;
import static com.yummy.entity.QRcpBase.rcpBase;
import static org.springframework.util.StringUtils.isEmpty;

@Service
public class MyPageService {
    private final JPAQueryFactory queryFactory;

    public MyPageService(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /** 마이페이지 최근 찜한상품 3개 **/
    public List<RcpBase> getMyPageMbrRcpLike(MbrBaseDto mbrBaseDto) {

        List<RcpBase> rcpBaseList = queryFactory
                                    .select(mbrRcpLike.rcpBase)
                                    .from(mbrRcpLike)
                                    .leftJoin(mbrRcpLike.mbrBase, mbrBase)
                                    .where(mbrBasemMbrNodEq(mbrBaseDto.getMbrNo()))
                                    .orderBy(mbrRcpLike.sysModTime.desc())
                                    .limit(3)
                                    .fetch();
        return rcpBaseList;
    }


    /**
     * mbrNo null check 값 비교
     * @param mbrNo
     * @return BooleanExpression
     */
    private BooleanExpression mbrBasemMbrNodEq(Long mbrNo) {
        return isEmpty(mbrNo) ? null : mbrBase.mbrNo.eq(mbrNo);
    }
}
