package com.ejercicio1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealResponseDto {
    private Double totalCalories;
    private FoodDto maxIngredient;
    private List<FoodDto> ingredients;
}
