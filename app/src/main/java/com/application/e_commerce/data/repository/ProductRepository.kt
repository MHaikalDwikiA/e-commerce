package com.application.e_commerce.data.repository

import com.application.e_commerce.data.model.Product
import com.application.e_commerce.data.remote.RetrofitClient

class ProductRepository {
    private val api = RetrofitClient.apiService

    suspend fun getProducts(search: String? = null): Result<List<Product>> {
        return try {
            val response = api.getProducts(search)
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}