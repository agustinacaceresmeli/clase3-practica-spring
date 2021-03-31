package com.ejercicio1.controller;

import com.ejercicio1.dto.MealDto;
import com.ejercicio1.dto.MealResponseDto;
import com.ejercicio1.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calories")
public class CalculateController {

    @Autowired
    private CalculateService calculateService;

    @PostMapping("/calculate")
    public MealResponseDto calculate(@RequestBody MealDto meal){
        return calculateService.totalCalories(meal);
    }

    @PostMapping("/list")
    public List<MealResponseDto> calculateList(@RequestBody List<MealDto> mealDtoList){
        return calculateService.totalCaloriesList(mealDtoList);
    }

}
