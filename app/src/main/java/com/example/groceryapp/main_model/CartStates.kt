package com.example.groceryapp.main_model

import com.example.groceryapp.cart.model.CartUiItem

data class CartStates(
    val goods: List<CartUiItem> = emptyList(),
    val totalPrice: Double = 0.0,
    val isLoading: Boolean = false,
    val error: String? = null,
)
