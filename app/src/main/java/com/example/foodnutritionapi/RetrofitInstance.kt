package com.example.foodnutritionapi

import android.content.Context
import com.example.foodnutritionapi.data.Api
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance(private val context: Context, private val sessionId: String) {

    companion object {

        private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        private val headerInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-apihub-key", "gHWNKSwAKMut4fBv5a9PRhVIJkzT01qRizFNKWuO7PeZAu002u")
                .addHeader("x-apihub-host", "Jokes-API.allthingsdev.co")
                .addHeader("x-apihub-endpoint", "fba849ca-2257-445d-b5e2-ba6ce527a281") // Add the missing header
                .build()
            chain.proceed(request)
        }

        private val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(interceptor)
            .build()

        val api: Api = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Api.BASE_URL)
            .client(client)
            .build()
            .create(Api::class.java)
    }
}
