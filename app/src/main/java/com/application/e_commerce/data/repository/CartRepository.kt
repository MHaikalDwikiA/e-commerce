package com.application.e_commerce.data.repository

import com.application.e_commerce.data.model.CartItem
import com.application.e_commerce.data.model.CartRequest
import com.application.e_commerce.data.model.CartUpdateRequest
import com.application.e_commerce.data.remote.RetrofitClient

class CartRepository {
    private val api = RetrofitClient.apiService

    suspend fun getCart(): Result<List<CartItem>> {
        return try {
            val response = api.getCart()
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun addToCart(item: CartRequest): Result<CartItem> {
        return try {
            val response = api.addToCart(item)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateCartQuantity(id: String, quantity: Int): Result<CartItem> {
        return try {
            val response = api.updateCartQuantity(id, CartUpdateRequest(quantity))
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteCartItem(id: String): Result<CartItem> {
        return try {
            val response = api.deleteCartItem(id)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}