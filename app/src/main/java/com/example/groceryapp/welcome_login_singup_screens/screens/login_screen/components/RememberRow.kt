package com.example.groceryapp.welcome_login_singup_screens.screens.login_screen.components

import android.graphics.Color
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groceryapp.ui.theme.backgroundColor1
import com.example.groceryapp.ui.theme.blackColor
import com.example.groceryapp.ui.theme.greyColor
import com.example.groceryapp.ui.theme.linkColor
import com.example.groceryapp.ui.theme.primaryDarkColor
import com.example.groceryapp.ui.theme.whiteColor


@Composable
fun RememberRow (
    onSwitch:() -> Unit = {},
    onClick:() -> Unit = {},

){
    var isChecked by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 0.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Switch(
            checked = isChecked,
            onCheckedChange = { isChecked = it },
            modifier = Modifier.scale(0.60f), // Уменьшаем стандартный Switch до размеров как на картинке
            colors = SwitchDefaults.colors(
                // Цвета для выключенного состояния (как на фото)
                uncheckedThumbColor = greyColor,     // Серый кружок
                uncheckedTrackColor = backgroundColor1,     // Прозрачный фон внутри
                uncheckedBorderColor = greyColor,    // Серая обводка

                // Цвета для включенного состояния (настройте под свой дизайн)
                checkedThumbColor = whiteColor,
                checkedTrackColor = primaryDarkColor        // Например, синий фон при активации
            )
        )
        Text(
            text = "Remember me",
            color = greyColor,
            fontSize = 14.sp,
            modifier = Modifier
                .weight(1f)


        )

        Text(
            text = "Forgot password",
            color = linkColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(end = 10.dp)
                .clickable{
                    onClick()
                }
        )

    }
}