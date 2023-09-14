package com.yummy.service;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yummy.dto.RcpBaseDto;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.yummy.entity.QMealPlanner.mealPlanner;
import static com.yummy.entity.QMealPlannerMapping.mealPlannerMapping;
import static com.yummy.entity.QRcpBase.rcpBase;

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
                mealPlannerDto.getDelYn()
        );
        mealPlannerRepository.save(mealPlanner);

        Long mpNo = mealPlanner.getMpNo();

        //식단 매핑정보 추가
        Arrays.stream(mealPlannerDto.getRcpNos().split(","))
                .forEach(r -> {
                    //MealPlannerMapping mealPlannerMapping = new MealPlannerMapping(mpNo, Long.parseLong(r));
                    //MealPlannerMapping mealPlannerMapping = new MealPlannerMapping(mpNo, Long.parseLong(r));
                    mealPlannerMappingRepository.save(new MealPlannerMapping());
                });
    }

    public Page<MealPlannerDto> selectMealPlannerList(Pageable pageable) {
        QueryResults<MealPlannerDto> result = queryFactory.select(new QMealPlannerDto(
                        mealPlannerMapping.mealPlanner.mpNo,
                        mealPlanner.mpName,
                        mealPlanner.mpDescription,
                        mealPlanner.mbrNo,
                        mealPlanner.delYn,
                        mealPlannerMapping.rcpBase.rcpNo
                ))
                .from(mealPlannerMapping)
                .leftJoin(mealPlannerMapping.mealPlanner, mealPlanner)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MealPlannerDto> content = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    public Page<MealPlannerDto> selectMealPlannerListAll(Pageable pageable) {
        QueryResults<MealPlannerDto> result = queryFactory.select(new QMealPlannerDto(
                        mealPlanner.mpNo.as("mpNo"),
                        mealPlanner.mpName.as("mpName"),
                        mealPlanner.mpDescription.as("mpDescription"),
                        mealPlanner.mbrNo.as("mbrNo"),
                        mealPlanner.delYn.as("delYn"),
                        rcpBase.rcpNo.as("rcpNo"),
                        rcpBase.rcpName.as("rcpName"),
                        rcpBase.rcpDescription.as("rcpDescription")
                ))
                .from(mealPlannerMapping)
                .leftJoin(mealPlannerMapping.mealPlanner, mealPlanner)
                .leftJoin(mealPlannerMapping.rcpBase, rcpBase)
                .orderBy(mealPlannerMapping.sysModTime.asc())
                .fetchResults();

        List<MealPlannerDto> content = result.getResults();
        //long total = result.getTotal();

        // 식단번호로 그룹핑하여 List 재정의
        Map<Long, List<MealPlannerDto>> groupingContent = content.stream().collect(Collectors.groupingBy(MealPlannerDto::getMpNo));
        List<MealPlannerDto> resultContent = new ArrayList<>();
        for(Long k : groupingContent.keySet()){
            List<MealPlannerDto> mealPlannerDtoList = groupingContent.get(k);
            List<RcpBaseDto> rcpBaseDtoList = mealPlannerDtoList.stream().map(m -> {
                RcpBaseDto rcpBaseDto = new RcpBaseDto();
                rcpBaseDto.setRcpNo(m.getRcpNo());
                rcpBaseDto.setRcpName(m.getRcpName());
                rcpBaseDto.setRcpDescription(m.getRcpDescription());
                return rcpBaseDto;
            }).collect(Collectors.toList());

            MealPlannerDto mealPlannerDto = mealPlannerDtoList.get(0);
            mealPlannerDto.setRcpNo(null);
            mealPlannerDto.setRcpName(null);
            mealPlannerDto.setRcpDescription(null);
            mealPlannerDto.setRcpBaseList(rcpBaseDtoList);

            resultContent.add(mealPlannerDto);
        }
        long total = resultContent.size();

        return new PageImpl<>(resultContent, pageable, total);
    }

    public List<MealPlannerDto> selectMealPlannerListV2(Pageable pageable) {
        QueryResults<MealPlannerDto> result =
                queryFactory
                        .select(new QMealPlannerDto(
                                mealPlannerMapping.mealPlanner.mpNo,
                                mealPlanner.mpName,
                                mealPlanner.mpDescription,
                                mealPlanner.mbrNo,
                                mealPlanner.delYn,
                                mealPlannerMapping.rcpBase.rcpNo
                        ))
                        .from(mealPlannerMapping)
                        .leftJoin(mealPlannerMapping.mealPlanner, mealPlanner)
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetchResults();

        return result.getResults();
    }
}
