package com.example.groceryapp.welcome_login_singup_screens.screens.login_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groceryapp.ui.theme.blackColor


@Composable
fun BottomRowL (
    onClick:() -> Unit = {}
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Don’t have an account ?",
            color = blackColor,
            fontSize = 15.sp,
            fontWeight = FontWeight.Thin
        )
        Text(
            text = "Sign up",
            color = blackColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(start = 5.dp)
                .clickable{
                    onClick()
                }
        )

    }
}