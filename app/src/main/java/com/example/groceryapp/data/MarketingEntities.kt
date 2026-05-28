package com.example.groceryapp.data

import androidx.room.Entity


@Entity(
    tableName = "discount_products",
    primaryKeys = ["productId"]
)

data class DiscountEntity (
    val productId: Int,
    val discountValue: Int,
)

@Entity(
    tableName = "new_products",
    primaryKeys = ["productId"]
)

data class NewProductEntity (
    val productId: Int
)

