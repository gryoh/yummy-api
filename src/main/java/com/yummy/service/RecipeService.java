package com.yummy.service;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yummy.dto.MealPlannerDto;
import com.yummy.dto.QMealPlannerDto;
import com.yummy.dto.QRecipeDto;
import com.yummy.dto.RecipeDto;
import com.yummy.entity.QRcpBase;
import com.yummy.entity.QRcpStuff;
import com.yummy.entity.RcpStuff;
import com.yummy.repository.MealPlannerRepository;
import com.yummy.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

import static com.yummy.entity.QMealPlanner.mealPlanner;
import static com.yummy.entity.QMealPlannerMapping.mealPlannerMapping;
import static com.yummy.entity.QRcpBase.rcpBase;
import static com.yummy.entity.QRcpStuff.*;

@Service
public class RecipeService {
    private final JPAQueryFactory queryFactory;
    public RecipeService(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    //@Autowired
    //RecipeRepository recipeRepository;

    public Page<RecipeDto> selectRecpieList(Pageable pageable) {
        QueryResults<RecipeDto> result = queryFactory.select(new QRecipeDto(
                        rcpBase.rcpNo,
                        rcpBase.rcpName,
                        rcpBase.rcpDescription,
                        rcpBase.useYn,
                        rcpBase.filePath,
                        rcpBase.fileName
                ))
                .from(rcpBase)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<RecipeDto> content = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    public Page<RecipeDto> selectRecpieList2(Pageable pageable) {
        QueryResults<RecipeDto> result = queryFactory.select(new QRecipeDto(
                        rcpBase.rcpNo,
                        rcpBase.rcpName,
                        rcpBase.rcpDescription,
                        rcpBase.useYn,
                        rcpBase.filePath,
                        rcpBase.fileName
                ))
                .from(rcpBase)
                .leftJoin(rcpBase.rcpStuffs, rcpStuff)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<RecipeDto> content = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(content, pageable, total);
    }
}
