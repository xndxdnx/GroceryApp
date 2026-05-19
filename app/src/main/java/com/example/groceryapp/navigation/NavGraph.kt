package com.example.groceryapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.groceryapp.preview_splash_screens.screens.FirstSplashScreen
import com.example.groceryapp.preview_splash_screens.screens.SecondSplashScreen
import com.example.groceryapp.preview_splash_screens.screens.ThirdSplashScreen
import com.example.groceryapp.welcome_login_singup_screens.screens.signup_screen.SignUpScreen
import com.example.groceryapp.welcome_login_singup_screens.screens.login_screen.LoginScreen
import com.example.groceryapp.welcome_login_singup_screens.screens.welcome_screen.WelcomeScreen
import kotlinx.serialization.Serializable

@Serializable
object FirstSplashRoute

@Serializable
object SecondSplashRoute

@Serializable
object ThirdSplashRoute

@Serializable
object WelcomeScreenRoute

@Serializable
object SignUpScreenRoute

@Serializable
object LoginScreenRoute

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = FirstSplashRoute

    ) {
        composable<FirstSplashRoute> {
            FirstSplashScreen(onButtonClick = {
                navController.navigate(SecondSplashRoute) {
                    popUpTo<FirstSplashRoute> { inclusive = true }
                }
            })
        }
        composable<SecondSplashRoute> {
            SecondSplashScreen(onButtonClick = {
                navController.navigate(ThirdSplashRoute) {
                    popUpTo<SecondSplashRoute> { inclusive = true }
                }
            })
        }
        composable<ThirdSplashRoute> {
            ThirdSplashScreen(
                onButtonClick = {
                    navController.navigate(WelcomeScreenRoute)
                },
                onButtonScipClick = {
                    navController.navigate(WelcomeScreenRoute)
                }
            )
        }

        composable<WelcomeScreenRoute> {
            WelcomeScreen(
                loginClick = { navController.navigate(LoginScreenRoute) {} },
                createAccountClick = { navController.navigate(SignUpScreenRoute) {} },
                backClick = { navController.popBackStack() }
            )
        }

        composable<SignUpScreenRoute> {
            SignUpScreen(
                loginClick = {
                    navController.navigate(LoginScreenRoute) {
                        launchSingleTop = true
                    }
                },
                backClick = { navController.popBackStack() }
            )
        }

        composable<LoginScreenRoute> {
            LoginScreen(
                signUpClick = {
                    navController.navigate(SignUpScreenRoute) {
                        launchSingleTop = true
                    }
                },
                backClick = { navController.popBackStack() }
            )
        }
    }
}