package com.yummy.service;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yummy.dto.MealPlannerDto;
import com.yummy.dto.QMealPlannerDto;
import com.yummy.entity.*;
import com.yummy.repository.MealPlannerMappingRepository;
import com.yummy.repository.MealPlannerRepository;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static com.yummy.entity.QRcpBase.rcpBase;
import static com.yummy.entity.QMealPlanner.mealPlanner;
import static com.yummy.entity.QMealPlannerMapping.mealPlannerMapping;
import static org.springframework.util.StringUtils.isEmpty;

@Service
public class MealPlannerService {

    private final JPAQueryFactory queryFactory;
    public MealPlannerService(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Autowired
    MealPlannerRepository mealPlannerRepository;

    @Autowired
    MealPlannerMappingRepository mealPlannerMappingRepository;

    @Transactional
    public void insertMealPlanner(MealPlannerDto mealPlannerDto){
        // 식단 추가
        MealPlanner mealPlanner = new MealPlanner(
                mealPlannerDto.getMpName(),
                mealPlannerDto.getMpDescription(),
                mealPlannerDto.getMbrNo(),
                "N"
        );
        mealPlannerRepository.save(mealPlanner);

        Long mpNo = mealPlanner.getMpNo();

        //식단 매핑정보 추가
        Arrays.stream(mealPlannerDto.getRcpNos().split(","))
                .map(Long::parseLong)
                .forEach(r -> {
                    //MealPlannerMapping mealPlannerMapping = new MealPlannerMapping(mpNo, Long.parseLong(r));
                    //MealPlannerMapping mealPlannerMapping = new MealPlannerMapping(mpNo, Long.parseLong(r));
                    mealPlannerMappingRepository.save(new MealPlannerMapping(mpNo, r));
                });
    }

    public Page<MealPlannerDto> selectMealPlannerList(Pageable pageable, long mbrNo) {
        QueryResults<MealPlannerDto> result = queryFactory.select(new QMealPlannerDto(
                        mealPlannerMapping.mealPlanner.mpNo,
                        mealPlanner.mpDescription,
                        rcpBase.rcpNo,
                        rcpBase.rcpName,
                        rcpBase.rcpDescription,
                        rcpBase.filePath,
                        rcpBase.fileName
                ))
                .from(mealPlannerMapping)
                .leftJoin(mealPlannerMapping.mealPlanner, mealPlanner)
                .leftJoin(mealPlannerMapping.rcpBase, rcpBase)
                .where(
                        mealPlannerMbrNoEq(mbrNo)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MealPlannerDto> content = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    public List<MealPlannerDto> selectMealPlannerListV2(Pageable pageable) {
        QueryResults<MealPlannerDto> result = queryFactory.select(new QMealPlannerDto(
                    mealPlannerMapping.mealPlanner.mpNo,
                    mealPlanner.mpDescription,
                    rcpBase.rcpNo,
                    rcpBase.rcpName,
                    rcpBase.rcpDescription,
                    rcpBase.filePath,
                    rcpBase.fileName
                ))
                .from(mealPlannerMapping)
                .leftJoin(mealPlannerMapping.mealPlanner, mealPlanner)
                .leftJoin(mealPlannerMapping.rcpBase, rcpBase)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return result.getResults();
    }

    private BooleanExpression mealPlannerMbrNoEq(long mbrNo) {
        return isEmpty(mbrNo) ? null : mealPlanner.mbrNo.eq(mbrNo);
    }
}
