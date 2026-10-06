package com.application.e_commerce.data.remote

import com.application.e_commerce.data.model.CartItem
import com.application.e_commerce.data.model.CartRequest
import com.application.e_commerce.data.model.CartUpdateRequest
import com.application.e_commerce.data.model.Product
import retrofit2.Response
import retrofit2.http.*

interface ShopeeApiService {
    @GET("products")
    suspend fun getProducts(
        @Query("search") search: String? = null
    ): Response<List<Product>>

    @GET("cart")
    suspend fun getCart(): Response<List<CartItem>>

    @POST("cart")
    suspend fun addToCart(
        @Body request: CartRequest
    ): Response<CartItem>

    @PUT("cart/{id}")
    suspend fun updateCartQuantity(
        @Path("id") id: String,
        @Body request: CartUpdateRequest
    ): Response<CartItem>

    @DELETE("cart/{id}")
    suspend fun deleteCartItem(
        @Path("id") id: String
    ): Response<CartItem>
}