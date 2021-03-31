package com.ejercicio1.service;

import com.ejercicio1.dto.MealDto;
import com.ejercicio1.dto.MealResponseDto;
import java.util.List;

public interface CalculateService {
    MealResponseDto totalCalories(MealDto mealDto);
    List<MealResponseDto> totalCaloriesList(List<MealDto> mealList);

}
