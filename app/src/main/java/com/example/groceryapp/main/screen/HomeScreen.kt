package com.example.groceryapp.main.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.groceryapp.main.screen.components.PromotionComponent
import com.example.groceryapp.welcome_login_singup_screens.screens.signup_screen.components.SearchField

@Composable
fun HomeScreen () {
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {
            item { SearchField()  }
            item { PromotionComponent()  }

        }
    }
}