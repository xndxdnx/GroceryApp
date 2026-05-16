package com.example.groceryapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.groceryapp.preview_splash_screens.screens.FirstSplashScreen
import com.example.groceryapp.preview_splash_screens.screens.SecondSplashScreen
import com.example.groceryapp.preview_splash_screens.screens.ThirdSplashScreen
import kotlinx.serialization.Serializable


@Serializable object Home
@Serializable object FirstSplashDestination
@Serializable object SecondSplashDestination
@Serializable object ThirdSplashDestination

@Composable
fun NavGraph(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = FirstSplashDestination,

    ) {
        composable<FirstSplashDestination> {
            FirstSplashScreen(onButtonClick = { navController.navigate(SecondSplashDestination) } )
        }
        composable<SecondSplashDestination>{
            SecondSplashScreen(onButtonClick = {navController.navigate(ThirdSplashDestination) } )
        }
        composable<ThirdSplashDestination>{ ThirdSplashScreen() }

    }
}