package com.example.foodnutritionapi.data

import com.example.foodnutritionapi.data.model.JokesAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class JokesRepoImpl(
    private val api: Api
) : JokesRepo {
    override suspend fun postJoke(): Flow<Result<JokesAPI>> {
        return flow {
            try {
                val response = api.postJoke()
                if (response.isSuccessful) {
                    val joke = response.body()
                    if (joke != null) {
                        emit(Result.Success(joke))
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
