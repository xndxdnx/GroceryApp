package com.example.groceryapp.welcome_login_singup_screens.screens.welcome_screen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groceryapp.ui.theme.blackColor
import com.example.groceryapp.ui.theme.greyColor


@Composable
fun CardTopRows (){
    Text(
        text = "Welcome",
        fontSize = 25.sp,
        color = blackColor,
        fontWeight = FontWeight.SemiBold
    )
    Text(
        text = "Lorem ipsum dolor sit amet, consetetur \n" +
                "sadipscing elitr, sed diam nonumy",
        fontSize = 15.sp,
        color = greyColor,
        modifier = Modifier
            .padding(top = 8.dp)
    )
}