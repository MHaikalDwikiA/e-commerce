package com.application.e_commerce.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: String,
    @SerializedName("category") val category: String,
    @SerializedName("rating") val rating: String,
    @SerializedName("imgUrl") val imgUrl: String,
    @SerializedName("description") val description: String
)