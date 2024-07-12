package com.example.foodnutritionapi.data

import com.example.foodnutritionapi.data.model.Nutrition
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class NutritionRepoImpl(
    private val api: Api
) : NutritionRepo {
    override suspend fun postNutrition(): Flow<Result<Nutrition>> {
        return flow {
            try {
                val response = api.postNutrition()
                if (response.isSuccessful) {
                    val nutrition = response.body()
                    if (nutrition != null) {
                        emit(Result.Success(nutrition))
                    } else {
                        emit(Result.Failure("No data found"))
                    }
                } else {
                    emit(Result.Failure("Error: ${response.code()} ${response.message()}"))
                }
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Failure("Network Error"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Failure("Server Error: ${e.message}"))
            }
        }
    }
}