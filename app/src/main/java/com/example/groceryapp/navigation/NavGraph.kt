package com.example.groceryapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.groceryapp.R
import com.example.groceryapp.preview_splash_screens.screens.FirstSplashScreen
import com.example.groceryapp.preview_splash_screens.screens.SecondSplashScreen
import com.example.groceryapp.preview_splash_screens.screens.ThirdSplashScreen
import com.example.groceryapp.welcome_login_singup_screens.screens.signup_screen.SignUpScreen
import com.example.groceryapp.welcome_login_singup_screens.screens.login_screen.LoginScreen
import com.example.groceryapp.welcome_login_singup_screens.screens.welcome_screen.WelcomeScreen
import kotlinx.serialization.Serializable


// Графы для объединения
@Serializable
object SplashGraph

@Serializable
object AuthGraph

@Serializable
object MainScreenRoute // Сам экран с Bottom Bar


@Serializable
object MainGraph            // Граф главного экрана

// Цели для вкладок Bottom Bar
@Serializable object CatalogRoute
@Serializable object CartRoute
@Serializable object ProfileRoute
@Serializable object FavoriteRoute

sealed class BottomBarItem<T : Any>(
    val route: T,
    val iconRes: Int
) {
    object Catalog : BottomBarItem<CatalogRoute>(CatalogRoute, R.drawable.catalog_ic)
    object Cart : BottomBarItem<CartRoute>(CartRoute, R.drawable.cart_icon)
    object Profile : BottomBarItem<ProfileRoute>(ProfileRoute, R.drawable.create_an_account_logo)
    object Favorite : BottomBarItem<FavoriteRoute>(FavoriteRoute, R.drawable.favorite_icon)
}

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
        startDestination = SplashGraph

    ) {
        // Граф сплеш-экранов
        navigation<SplashGraph>(startDestination = FirstSplashRoute) {
            composable<FirstSplashRoute> {
                FirstSplashScreen(onButtonClick = {
                    navController.navigate(SecondSplashRoute)
                })
            }
            composable<SecondSplashRoute> {
                SecondSplashScreen(onButtonClick = {
                    navController.navigate(ThirdSplashRoute) {
                        popUpTo(FirstSplashRoute)
                        popUpTo(SecondSplashRoute)
                    }
                })
            }
            composable<ThirdSplashRoute> {
                ThirdSplashScreen(
                    onButtonClick = {
                        navController.navigate(AuthGraph) {

                        }
                    },
                    onButtonScipClick = {
                        navController.navigate(AuthGraph) {

                        }
                    }
                )
            }
        }

        navigation<AuthGraph>(startDestination = WelcomeScreenRoute) {
            composable<WelcomeScreenRoute> {
                WelcomeScreen(
                    loginClick = {
                        navController.navigate(LoginScreenRoute) {
                            launchSingleTop = true
                        }
                    },
                    createAccountClick = {
                        navController.navigate(SignUpScreenRoute) {
                            launchSingleTop = true
                        }
                    },
                    backClick = { navController.popBackStack() }
                )
            }
            composable<SignUpScreenRoute> {
                SignUpScreen(
                    loginClick = {
                        navController.navigate(LoginScreenRoute) {
                            popUpTo(WelcomeScreenRoute) { inclusive = false }
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
                            popUpTo(WelcomeScreenRoute) { inclusive = false }
                            launchSingleTop = true
                        }
                    },
                    backClick = { navController.popBackStack() }
                )
            }
        }
    }
}