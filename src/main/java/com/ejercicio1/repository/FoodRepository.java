package com.ejercicio1.repository;

import com.ejercicio1.dto.FoodDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
public class FoodRepository {

    public static FoodDto findCalorieByIngredient(String ingredient) {
        List<FoodDto> food = loadDataBase();
        FoodDto result = null;
        if (food != null){
            Optional<FoodDto> item = food.stream().
                    filter(caloriesDTO -> caloriesDTO.getName().equals(ingredient)).
                    findFirst();
            if (item.isPresent())
                result = item.get();
        }
        return result;
    }

    private static List<FoodDto> loadDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:food.json");

        } catch (IOException e){
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<FoodDto>> typeReference = new TypeReference<>(){};
        List<FoodDto> priceDTOS = null;

        try{
            priceDTOS = objectMapper.readValue(file, typeReference);
        } catch (IOException e){
            e.printStackTrace();
        }
        return priceDTOS;
    }

}
