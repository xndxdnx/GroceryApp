package com.example.groceryapp.favorites.data

import androidx.room.Entity

@Entity(
    tableName = "favorite_products",
    primaryKeys = ["userId", "productId"]
)

data class FavoriteProductEntity(
    val userId: String,
    val productId: Int
)
