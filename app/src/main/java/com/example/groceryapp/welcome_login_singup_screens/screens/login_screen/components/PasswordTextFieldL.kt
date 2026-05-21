package com.example.groceryapp.welcome_login_singup_screens.screens.signup_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groceryapp.R
import com.example.groceryapp.ui.theme.blackColor
import com.example.groceryapp.ui.theme.greyColor
import com.example.groceryapp.ui.theme.primaryDarkColor
import com.example.groceryapp.ui.theme.whiteColor

@Composable
fun PasswordTextFieldL(
    value: String,
    onValueChange: (String) -> Unit
) {

    var isPasswordVisible by remember { mutableStateOf(false) }

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
        // 2. Управляем отображением текста: точки или обычный текст
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        // Важно для клавиатуры: показываем тип поля "Пароль"
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

        // Иконка замка слева
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.lock_ic),
                contentDescription = null,
                tint = greyColor,
                modifier = Modifier.padding(start = 15.dp) // перенесли отступ из плейсхолдера
            )
        },

        // Интерактивная иконка глаза справа
        trailingIcon = {
            IconButton(
                onClick = { isPasswordVisible = !isPasswordVisible },
                modifier = Modifier.padding(end = 15.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.eye_ic),
                    contentDescription = null,
                    tint = if(isPasswordVisible) primaryDarkColor else greyColor
                )
            }
        },

        // Плейсхолдер теперь содержит только подсказку
        placeholder = {
            Text(
                text = "Password", // Лучше написать текст, так как точки уже будут при вводе
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