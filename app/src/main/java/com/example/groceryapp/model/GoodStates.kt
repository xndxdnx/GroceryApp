package com.example.groceryapp.model

data class GoodStates(
    val goods: List<Good> = emptyList(),
    val favoriteProductIds: List<Int> = emptyList(),
    val cartProductsCount: Map<Int, Int> = emptyMap(),
    val selectedCategory: Category = Category.VEGETABLES,
    val isLoading: Boolean = false,
    val isNew: Boolean = false,
    val error: String? = null,
    val searchQuery: String = ""
    )
