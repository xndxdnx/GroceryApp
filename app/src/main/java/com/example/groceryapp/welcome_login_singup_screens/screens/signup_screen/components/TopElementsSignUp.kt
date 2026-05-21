package com.example.groceryapp.welcome_login_singup_screens.screens.signup_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groceryapp.R
import com.example.groceryapp.ui.theme.whiteColor

@Composable
@Preview
fun TopElementsSignUp (
    backClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 50.dp),
        ) {
        Icon(
            painter = painterResource(R.drawable.back_arrow),
            contentDescription = null,
            tint = whiteColor,
            modifier = Modifier
                .padding(start = 15.dp, top = 5.dp)
                .clickable{
                    backClick()
                }
                .size(width = 23.dp, height = 16.dp)
        )
        Text(
            text = "Welcome",
            fontSize = 25.sp,
            color = whiteColor,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(),
        )
    }

}