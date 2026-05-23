package com.example.groceryapp.cart.data

import androidx.room.Entity


@Entity(
    tableName = "cart_products",
    primaryKeys = [
        "userId",
        "productId"
    ]
)
data class CartProductEntity (
    val userId: String,
    val productId: Int,
    val quantity: Int
)