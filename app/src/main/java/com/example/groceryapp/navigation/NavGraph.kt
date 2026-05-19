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
import com.example.groceryapp.welcome_login_singup_screens.screens.create_account_screen.CreateAccountScreen
import com.example.groceryapp.welcome_login_singup_screens.screens.login_screen.LoginScreen
import com.example.groceryapp.welcome_login_singup_screens.screens.welcome_screen.WelcomeScreen
import kotlinx.serialization.Serializable


@Serializable object Home
@Serializable object FirstSplashDestination
@Serializable object SecondSplashDestination
@Serializable object ThirdSplashDestination
@Serializable object WelcomeScreenDestination
@Serializable object CreateAccountScreenDestination

@Serializable object LoginScreenDestination
@Composable
fun NavGraph(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = FirstSplashDestination

    ) {
        composable<FirstSplashDestination> {
            FirstSplashScreen(onButtonClick = { navController.navigate(SecondSplashDestination) } )
        }
        composable<SecondSplashDestination>{
            SecondSplashScreen(onButtonClick = {navController.navigate(ThirdSplashDestination) } )
        }
        composable<ThirdSplashDestination>{
            ThirdSplashScreen(
                onButtonClick = {navController.navigate(WelcomeScreenDestination)},
                onButtonScipClick = {navController.navigate(WelcomeScreenDestination)}

        ) }

        composable<WelcomeScreenDestination>{
            WelcomeScreen(loginClick = { navController.navigate(LoginScreenDestination) })
        }

        composable<CreateAccountScreenDestination>{
            CreateAccountScreen(loginClick = { navController.navigate(LoginScreenDestination) })
        }

        composable<LoginScreenDestination>{
            LoginScreen(signUpClick = { navController.navigate(CreateAccountScreenDestination) })
        }
    }
}