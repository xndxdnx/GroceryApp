package com.example.groceryapp.navigation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.groceryapp.R
import com.example.groceryapp.home.screen.HomeScreen
import com.example.groceryapp.ui.theme.backgroundColor1
import com.example.groceryapp.ui.theme.greyColor
import com.example.groceryapp.ui.theme.primaryDarkColor
import com.example.groceryapp.ui.theme.whiteColor
import kotlinx.serialization.Serializable

@Serializable
object MainGraph            // Граф главного экрана

// Цели для вкладок Bottom Bar
@Serializable
object HomeRoute
@Serializable
object CartRoute
@Serializable
object ProfileRoute
@Serializable
object FavoriteRoute

sealed class BottomBarItem<T : Any>(
    val route: T,
    val iconRes: Int
) {
    object Catalog : BottomBarItem<HomeRoute>(HomeRoute, R.drawable.catalog_ic       )
    object Cart : BottomBarItem<CartRoute>(CartRoute, R.drawable.cart_icon2)
    object Profile : BottomBarItem<ProfileRoute>(ProfileRoute, R.drawable.create_an_account_logo)
    object Favorite : BottomBarItem<FavoriteRoute>(FavoriteRoute, R.drawable.favorite_icon)
}


@Composable
fun MainHost () {

    val bottomNavController = rememberNavController()

    val bottomBarItems = listOf(
        BottomBarItem.Catalog,
        BottomBarItem.Cart,
        BottomBarItem.Profile,
        BottomBarItem.Favorite,
    )

    Scaffold(
        containerColor = backgroundColor1,

        bottomBar = {
            NavigationBar(
                containerColor = whiteColor,
                modifier = Modifier
                    .height(70.dp)

            ) {
                val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                bottomBarItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute?.toString()?.contains(item.route::class.qualifiedName ?: "") == true,
                        onClick = {
                            bottomNavController.navigate(item.route) {
                                popUpTo(bottomNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            val scale by animateFloatAsState(
                                targetValue = if (currentRoute == item.route) 1.5f else 1f,
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessLow
                                ),
                                label = ""
                            )
                            Icon(painter = painterResource(item.iconRes),
                                contentDescription = null,
                                modifier = Modifier.graphicsLayer {
                                    scaleX = scale
                                    scaleY = scale
                                }
                                    .size(22.dp)

                            )
                        },colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = whiteColor,
                            selectedTextColor = whiteColor,
                            indicatorColor = primaryDarkColor,
                            unselectedIconColor = greyColor,
                            unselectedTextColor = whiteColor
                        ),


                    )
                }

            }
        }
    ) { paddingValues ->
        NavHost(
            navController = bottomNavController,
            startDestination = HomeRoute,
            modifier = Modifier.padding(paddingValues)
        ){
            composable<HomeRoute> {
                HomeScreen()
            }
            composable<CartRoute> {
                // Ваш экран корзины: CartScreen()
            }
            composable<ProfileRoute> {
                // Ваш экран профиля: ProfileScreen()
            }
            composable<FavoriteRoute> {
                // Ваш экран профиля: ProfileScreen()
            }
        }
    }


}