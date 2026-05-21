package com.example.groceryapp.preview_splash_screens.data

import androidx.compose.ui.graphics.painter.Painter
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.groceryapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreensViewModel @Inject constructor(
) : ViewModel() {


    val description: String = "Lorem ipsum dolor sit amet, consetetur \n" +
            "sadipscing elitr, sed diam nonumy"
    val firstScreenImages = listOf(
        R.drawable.splash_1_1,
        R.drawable.splash_1_2,
        R.drawable.splash_1_3,
        R.drawable.splash_1_4,
    )

    val firstScreenTitle = listOf(
        "Welcome to",
        "Buy Quality \n" +
                "Dairy Products",
        "Buy Premium\n" +
                "Quality Fruits",
        "Get Discounts \n" +
                "On All Products"

    )

    val secondScreenImages = listOf(
        R.drawable.splash_2_1,
        R.drawable.splash_2_2,
        R.drawable.splash_2_3,
        R.drawable.splash_2_4,
    )
    val secondScreenTitle = listOf(
        "Premium Food\n" +
                "At Your Doorstep",
        "Buy Premium \n" +
                "Quality Fruits",
        "Buy Quality \n" +
                "Dairy Products",
        "Get Discounts \n" +
                "On All Products"
    )


    val thirdScreenImages = listOf(
        R.drawable.online_groceries_cuate,
        R.drawable.delivery_cuate,
        R.drawable.passionate_cuate,
    )
    val thirdScreenTitle = listOf(
        "Buy Grocery",
        "Fast Delivery",
        "Enjoy Quality Food"
    )






}