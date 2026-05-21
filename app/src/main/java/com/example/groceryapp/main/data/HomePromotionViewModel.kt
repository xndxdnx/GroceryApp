package com.example.groceryapp.main.data

import androidx.compose.ui.graphics.painter.Painter
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.groceryapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePromotionViewModel @Inject constructor(
) : ViewModel() {

    val firstScreenImages = listOf(
        R.drawable.splash_1_1,
        R.drawable.promotion_splash_1,
        R.drawable.splash_1_2,
        R.drawable.splash_1_3,
        R.drawable.splash_1_4,
    )

    val firstScreenTitle = listOf(
        "Welcome",
        "20% off on your\n" +
                "first purchase",
        "Buy Quality \n" +
                "Dairy Products",
        "Buy Premium\n" +
                "Quality Fruits",
        "Get Discounts \n" +
                "On All Products"

    )







}