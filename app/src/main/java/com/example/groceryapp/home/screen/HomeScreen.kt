package com.example.groceryapp.home.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.groceryapp.home.data.HomeEvent
import com.example.groceryapp.home.data.HomeViewModel
import com.example.groceryapp.home.screen.components.CategoryRow
import com.example.groceryapp.home.screen.components.CategorySelector
import com.example.groceryapp.home.screen.components.FeaturedRow
import com.example.groceryapp.home.screen.components.HomeGoods
import com.example.groceryapp.home.screen.components.PromotionComponent
import com.example.groceryapp.welcome_login_singup_screens.screens.signup_screen.components.SearchField

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item { SearchField() }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item { PromotionComponent() }
            item { Spacer(modifier = Modifier.height(12.dp)) }
            item { CategoryRow() }
            item { Spacer(modifier = Modifier.height(12.dp)) }
            item {
                CategorySelector(
                    onCategorySelected = {
                          viewModel.onEvent(HomeEvent.ChangeCategory(it))
                    }
                )
            }
            item { Spacer(modifier = Modifier.height(12.dp)) }
            item { FeaturedRow() }
            item { Spacer(modifier = Modifier.height(12.dp)) }
            item { HomeGoods() }

        }
    }
}