package com.example.foodnutritionapi.data

import com.example.foodnutritionapi.data.model.Nutrition
import kotlinx.coroutines.flow.Flow

interface NutritionRepo {
    //since the api response was JSON object we're gonna return an object
    suspend fun postNutrition(): Flow<Result<Nutrition>>
}