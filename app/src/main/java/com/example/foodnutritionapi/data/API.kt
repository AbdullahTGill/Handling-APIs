package com.example.foodnutritionapi.data

import com.example.foodnutritionapi.data.model.Nutrition
import retrofit2.Response
import retrofit2.http.POST

interface Api {
    @POST("nutrition-details") //path
    //since the api response was a JSON
    suspend fun postNutrition(): Response<Nutrition> // The response is a single Nutrition object

    //a good practice is to put the base url outside of the interface
    companion object {
        const val BASE_URL = "https://recipe-food-nutrition.proxy-production.allthingsdev.co/"
    }
}