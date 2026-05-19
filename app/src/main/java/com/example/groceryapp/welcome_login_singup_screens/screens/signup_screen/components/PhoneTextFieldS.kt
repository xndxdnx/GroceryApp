package com.example.groceryapp.welcome_login_singup_screens.screens.signup_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groceryapp.R
import com.example.groceryapp.ui.theme.blackColor
import com.example.groceryapp.ui.theme.greyColor
import com.example.groceryapp.ui.theme.whiteColor

@Composable
fun PhoneTextFieldS(
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = blackColor,
            textMotion = TextMotion.Animated
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),

        // Иконка слева
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.telephone_icon),
                contentDescription = null,
                tint = greyColor,
                modifier = Modifier.padding(start = 15.dp)
            )
        },
        placeholder = {
            Text(
                text = "Phone number", // Лучше написать текст, так как точки уже будут при вводе
                color = greyColor,
                fontSize = 15.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
        },
        shape = RoundedCornerShape(5.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = whiteColor,
            unfocusedContainerColor = whiteColor,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black,
            focusedTextColor = blackColor,
            unfocusedTextColor = blackColor
        ),
        singleLine = true,
    )
}