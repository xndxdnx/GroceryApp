package com.example.groceryapp.cart.model

import com.example.groceryapp.main_model.Good

data class CartUiItem(
    val good: Good,
    val quantity: Int
)
