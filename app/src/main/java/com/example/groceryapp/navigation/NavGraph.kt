package com.example.groceryapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.groceryapp.preview_splash_screens.screens.FirstSplashScreen
import com.example.groceryapp.preview_splash_screens.screens.SecondSplashScreen
import com.example.groceryapp.preview_splash_screens.screens.ThirdSplashScreen
import com.example.groceryapp.welcome_login_singup_screens.screens.signup_screen.SignUpScreen
import com.example.groceryapp.welcome_login_singup_screens.screens.login_screen.LoginScreen
import com.example.groceryapp.welcome_login_singup_screens.screens.welcome_screen.WelcomeScreen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.serialization.Serializable


// Графы для объединения
@Serializable
object SplashGraph

@Serializable
object AuthGraph

@Serializable
object MainScreenRoute // Сам экран с Bottom Bar

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

    val globalNavController = rememberNavController()

    val calculatedStartDestination = remember {
        if (FirebaseAuth.getInstance().currentUser != null) MainScreenRoute else SplashGraph
    }

    NavHost(
        navController = globalNavController,
        startDestination = calculatedStartDestination

    ) {
        // Граф сплеш-экранов
        navigation<SplashGraph>(startDestination = FirstSplashRoute) {
            composable<FirstSplashRoute> {
                FirstSplashScreen(onButtonClick = {
                    globalNavController.navigate(SecondSplashRoute)
                })
            }
            composable<SecondSplashRoute> {
                SecondSplashScreen(onButtonClick = {
                    globalNavController.navigate(ThirdSplashRoute) {
                        popUpTo(FirstSplashRoute)
                        popUpTo(SecondSplashRoute)
                    }
                })
            }
            composable<ThirdSplashRoute> {
                ThirdSplashScreen(
                    onButtonClick = {
                        globalNavController.navigate(AuthGraph) {

                        }
                    },
                    onButtonScipClick = {
                        globalNavController.navigate(AuthGraph) {

                        }
                    }
                )
            }
        }

        navigation<AuthGraph>(startDestination = WelcomeScreenRoute) {
            composable<WelcomeScreenRoute> {
                WelcomeScreen(
                    loginClick = {
                        globalNavController.navigate(LoginScreenRoute) {
                            launchSingleTop = true
                        }
                    },
                    createAccountClick = {
                        globalNavController.navigate(SignUpScreenRoute) {
                            launchSingleTop = true
                        }
                    },
                    backClick = { globalNavController.popBackStack() }
                )
            }
            composable<SignUpScreenRoute> {
                SignUpScreen(
                    loginClick = {
                        globalNavController.navigate(LoginScreenRoute) {
                            popUpTo(WelcomeScreenRoute) { inclusive = false }
                            launchSingleTop = true
                        }
                    },
                    backClick = { globalNavController.popBackStack() },
                    onSignUpSuccess = {
                        globalNavController.navigate(MainScreenRoute) {
                            popUpTo(AuthGraph) { inclusive = true }
                        }
                    }

                )
            }
            composable<LoginScreenRoute> {
                LoginScreen(
                    signUpClick = {
                        globalNavController.navigate(SignUpScreenRoute) {
                            popUpTo(WelcomeScreenRoute) { inclusive = false }
                            launchSingleTop = true
                        }
                    },
                    backClick = { globalNavController.popBackStack() },
                    onSignInSuccess = {
                        globalNavController.navigate(MainScreenRoute) {
                            popUpTo(AuthGraph) { inclusive = true }
                        }
                    }
                )
            }
        }
        composable<MainScreenRoute> {
            MainHost() // Вызываем экран с его внутренним Scaffold и навигатором
        }
    }
}