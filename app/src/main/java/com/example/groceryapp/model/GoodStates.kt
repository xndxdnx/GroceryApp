package com.example.groceryapp.model

data class GoodStates(
    val goods: List<Good> = emptyList(),
    val selectedCategory: Category = Category.VEGETABLES,
    val isLoading: Boolean = false,
    val isNew: Boolean = false,
    val error: String? = null,
    )
