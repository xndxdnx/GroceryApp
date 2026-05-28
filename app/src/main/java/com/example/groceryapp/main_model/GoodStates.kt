package com.example.groceryapp.main_model

data class GoodStates(
    val goods: List<Good> = emptyList(),
    val favoriteProductIds: List<Int> = emptyList(),
    val cartProductsCount: Map<Int, Int> = emptyMap(),
    val selectedCategory: Category = Category.VEGETABLES,
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",                        // строка поиска
    val newProductIds: List<Int> = emptyList(),          // Список ID товаров-новинок
    val discounts: Map<Int, Int> = emptyMap()            // Карта: ID товара -> Процент скидки
)

