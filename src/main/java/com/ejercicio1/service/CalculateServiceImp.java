package com.ejercicio1.service;

import com.ejercicio1.dto.FoodDto;
import com.ejercicio1.dto.IngredientDto;
import com.ejercicio1.dto.MealDto;
import com.ejercicio1.dto.MealResponseDto;
import com.ejercicio1.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculateServiceImp implements CalculateService{

    @Autowired
    private final FoodRepository foodRepository;

    public CalculateServiceImp(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public MealResponseDto totalCalories(MealDto mealDto) {
        MealResponseDto mealResponseDto = new MealResponseDto();
        totalCaloriesMeals(mealDto, mealResponseDto);
        return mealResponseDto;
    }

    public List<MealResponseDto> totalCaloriesList(List<MealDto> mealList) {
        List<MealResponseDto> mealResponseDtos = new ArrayList<>();
        for (int i = 0; i < mealList.size(); i++) {
            mealResponseDtos.add(new MealResponseDto());
            totalCaloriesMeals(mealList.get(i), mealResponseDtos.get(i));
        }
        return mealResponseDtos;
    }

    private void totalCaloriesMeals(MealDto mealDto, MealResponseDto mealResponseDto) {
        Double total = 0.0;
        Double max = 0.0;
        Double calorie;
        FoodDto maxCalories = null;
        List<FoodDto> ingredientsResponse = new ArrayList<>() {
        };
        for (IngredientDto ingredient:mealDto.getIngredients()) {
            FoodDto calories = FoodRepository.findCalorieByIngredient(ingredient.getName());
            calorie = (calories.getCalories() * ingredient.getWeight())/100;
            total += calorie;

            FoodDto caloriesDish = new FoodDto(ingredient.getName(), calorie);
            ingredientsResponse.add(caloriesDish);

            if (max < caloriesDish.getCalories()) {
                max = caloriesDish.getCalories();
                maxCalories = caloriesDish;
            }
        }
        mealResponseDto.setTotalCalories(total);
        mealResponseDto.setIngredients(ingredientsResponse);
        mealResponseDto.setMaxIngredient(maxCalories);
    }
}
