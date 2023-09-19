package com.yummy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.yummy.dto.MealPlannerDto;
import com.yummy.service.MealPlannerService;
import com.yummy.service.MemberService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mealPlanner")
public class MealPlannerController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MealPlannerService mealPlannerService;

    @PostMapping("/insertMealPlanner")
    @ResponseBody
    public MealPlannerDto insertMealPlanner(@RequestBody MealPlannerDto mealPlannerDto){
        // initDB에서 초기에 추가함(회원정보+재료+레시피+재료레시피)

        if(StringUtils.isEmpty(mealPlannerDto.getMbrNo())){
            throw new IllegalStateException("mbrNo가 null임");
        }

        // todo 레시피번호 목록을 가지고 식단에 레시피추가
        mealPlannerService.insertMealPlanner(mealPlannerDto);

        return mealPlannerDto;
    }

    @GetMapping("/v1/mealPlannerList")
    public Page<MealPlannerDto> mealPlannerList(@PageableDefault(size = 20, page = 0) Pageable pageable){
        //return mealPlannerService.selectMealPlannerList(pageable);
        return mealPlannerService.selectMealPlannerListAll(pageable);
    }

    @GetMapping("/v2/mealPlannerList")
    public List<MealPlannerDto> mealPlannerListV2(Pageable pageable){
        return mealPlannerService.selectMealPlannerListV2(pageable);
    }
}
