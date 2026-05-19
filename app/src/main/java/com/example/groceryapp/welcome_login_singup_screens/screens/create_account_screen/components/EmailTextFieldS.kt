package com.example.groceryapp.welcome_login_singup_screens.screens.create_account_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groceryapp.R
import com.example.groceryapp.ui.theme.greyColor
import com.example.groceryapp.ui.theme.whiteColor

@Composable
fun EmailTextFieldS(
   // email: String = "",
  // onEmailChange: (String) -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth(),
        value = email,
        onValueChange = { email = it },
        textStyle = TextStyle(
            fontSize = 22.sp,
            color = greyColor
        ),
        placeholder = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.mail_icon),
                    contentDescription = null,
                    tint = greyColor,
                    modifier = Modifier
                        .size(height = 18.dp, width = 23.dp)
                )

                Text(
                    text = "Email address",
                    color = greyColor,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(start = 15.dp)
                )
            }
        },
        shape = RoundedCornerShape(5.dp),

        colors = TextFieldDefaults.colors(
            focusedContainerColor = whiteColor,
            unfocusedContainerColor = whiteColor,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.White,
            focusedTextColor = Color.White
        ),
        singleLine = true,
    )
}