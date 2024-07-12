package com.example.foodnutritionapi

import com.example.foodnutritionapi.data.Api
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {

        //for logging
        private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        //Add a header interceptor
        //This is a better approach that using @header.
        private val headerInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-apihub-key", "gHWNKSwAKMut4fBv5a9PRhVIJkzT01qRizFNKWuO7PeZAu002u")
                .addHeader("x-apihub-host", "Recipe-Food-Nutrition.allthingsdev.co")
                .build()
            chain.proceed(request)
        }

        private val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(headerInterceptor) //Add the header interceptor
            .addInterceptor(interceptor) //the interceptor was http logging
            .build()

        // Create API instance
        val api: Api = Retrofit.Builder()
            //GSON is used to convert the JSON to binary objects so that they can be processed by the app
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Api.BASE_URL)
            .client(client)
            .build()
            .create(Api::class.java)
    }
}