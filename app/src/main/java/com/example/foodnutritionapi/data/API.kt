package com.example.foodnutritionapi.data

import com.example.foodnutritionapi.data.model.JokesAPI
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @GET("random_joke") // Replace with the actual endpoint path
    suspend fun postJoke(): Response<JokesAPI> // The response is a single JokesAPI object

    companion object {
        const val BASE_URL = "https://jokes-api.proxy-production.allthingsdev.co/"
    }
}
