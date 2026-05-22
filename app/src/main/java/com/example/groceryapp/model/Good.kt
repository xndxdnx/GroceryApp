package com.example.groceryapp.model

import androidx.compose.ui.graphics.Color
import com.example.groceryapp.ui.theme.greyColor

data class Good(
    val id: Int = 0,
    val title: String,
    val price: Double = 0.0,
    val description: String = "",
    val imageResId: Int,
    val weightTitle: String = "",
    val accentColor: Color = greyColor

)


