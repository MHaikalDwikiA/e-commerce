package com.application.e_commerce.data.model

import com.google.gson.annotations.SerializedName

data class CartItem(
    @SerializedName("id") val id: String? = null,
    @SerializedName("productId") val productId: String,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: String,
    @SerializedName("imgUrl") val imgUrl: String,
    @SerializedName("quantity") var quantity: Int
)

data class CartRequest(
    @SerializedName("productId") val productId: String,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: String,
    @SerializedName("imgUrl") val imgUrl: String,
    @SerializedName("quantity") val quantity: Int
)

data class CartUpdateRequest(
    @SerializedName("quantity") val quantity: Int
)