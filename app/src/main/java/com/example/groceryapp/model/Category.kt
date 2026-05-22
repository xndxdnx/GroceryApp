package com.example.groceryapp.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.groceryapp.R
import com.example.groceryapp.ui.theme.greyColor

enum class Category (
    val displayName: String,
    val iconResId: Int,
    val color: Color
) {
    VEGETABLES(displayName = "Vegetables", iconResId = R.drawable.vegetables_icon, Color(0xFF28B446)),
    FRUITS(displayName = "Fruits", iconResId = R.drawable.fruits_icon, Color(0xFFF8644A)),
    BEVERAGES(displayName = "Beverages", iconResId = R.drawable.beverages_icon,  Color(0xFFF5BA3C)),
    GROCERY(displayName = "Grocery", iconResId = R.drawable.grocery_icon, Color(0xFFAE80FF)),
    EDIBLE_OIL(displayName = "Edible Oil", iconResId = R.drawable.edible_oil_icon, Color(0xFF0CD4DC)),
    HOUSEHOLD(displayName = "Household", iconResId = R.drawable.household_icon, Color(0xFFFF7EB6))
}