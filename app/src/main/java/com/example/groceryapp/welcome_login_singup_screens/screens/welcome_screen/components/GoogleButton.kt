package com.example.groceryapp.welcome_login_singup_screens.screens.welcome_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groceryapp.R
import com.example.groceryapp.ui.theme.blackColor
import com.example.groceryapp.ui.theme.whiteColor

@Composable
@Preview
fun GoogleButton (
    onClick: () -> Unit = {},
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding()
            .size(height = 60.dp, width = 100.dp),
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = whiteColor
        ),
        elevation = ButtonDefaults.buttonElevation(2.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align (Alignment.CenterVertically)
        ) {
            Image(
                painter = painterResource(R.drawable.google_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(22.dp)
            )
            Text(
                text = "Continue with Google",
                fontSize = 15.sp,
                color = blackColor,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()

            )
        }
    }

}