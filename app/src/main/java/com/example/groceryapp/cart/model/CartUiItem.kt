package com.example.groceryapp.cart.model

import com.example.groceryapp.model.Good

data class CartUiItem(
    val good: Good,
    val quantity: Int
)
