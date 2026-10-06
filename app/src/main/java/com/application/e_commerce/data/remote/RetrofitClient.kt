package com.application.e_commerce.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://6ac512c354a61668c5f6d516.mockapi.io/api/v1/"

    val apiService: ShopeeApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ShopeeApiService::class.java)
    }
}