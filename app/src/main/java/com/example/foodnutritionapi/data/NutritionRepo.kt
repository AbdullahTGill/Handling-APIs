package com.example.foodnutritionapi.data

import com.example.foodnutritionapi.data.model.JokesAPI
import kotlinx.coroutines.flow.Flow

interface JokesRepo {
    suspend fun postJoke(): Flow<Result<JokesAPI>>
}
