package com.yummy.controller;

import com.yummy.dto.MealPlannerDto;
import com.yummy.dto.RecipeDto;
import com.yummy.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/rcp")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/v1/recipeList")
    public Page<RecipeDto> recipeList(@PageableDefault(size = 20, page = 0) Pageable pageable){
        return recipeService.selectRecpieList(pageable);
    }

    @GetMapping("/v2/recipeList")
    public Page<RecipeDto> recipeListV2(@PageableDefault(size = 20, page = 0) Pageable pageable){
        return recipeService.selectRecpieList(pageable);
    }
}
