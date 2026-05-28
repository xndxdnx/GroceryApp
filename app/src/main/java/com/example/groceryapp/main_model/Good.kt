package com.example.groceryapp.main_model

import androidx.compose.ui.graphics.Color
import com.example.groceryapp.ui.theme.greyColor

data class Good(
    val id: Int = 0,                    // ID
    val title: String,                  // Название товара
    val price: Double = 0.0,                // цена
    val description: String = "",           // описание товара
    val imageResId: Int,                    // картиночка товара
    val weightTitle: String = "",           // Вес товара за единицу
    val accentColor: Color = greyColor,     // цвет для кружёчка за товаром
)


