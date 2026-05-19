package com.example.groceryapp.welcome_login_singup_screens.screens.welcome_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groceryapp.R
import com.example.groceryapp.ui.theme.backgroundColor1
import com.example.groceryapp.ui.theme.backgroundColor2
import com.example.groceryapp.ui.theme.blackColor
import com.example.groceryapp.ui.theme.greyColor
import com.example.groceryapp.ui.theme.primaryDarkColor
import com.example.groceryapp.ui.theme.whiteColor
import com.example.groceryapp.welcome_login_singup_screens.screens.welcome_screen.components.BottomRow
import com.example.groceryapp.welcome_login_singup_screens.screens.welcome_screen.components.CardTopRows
import com.example.groceryapp.welcome_login_singup_screens.screens.welcome_screen.components.CreateButton
import com.example.groceryapp.welcome_login_singup_screens.screens.welcome_screen.components.GoogleButton
import com.example.groceryapp.welcome_login_singup_screens.screens.welcome_screen.components.TopElementsWelcome

@Composable
@Preview
fun WelcomeScreen(
    continueWithGoogleClick: () -> Unit = {},
    createAccountClick: () -> Unit = {},
    loginClick: () -> Unit = {},
    backClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Image(
            painter = painterResource(R.drawable.welcome_picture),
            contentDescription = null,
        )
        TopElementsWelcome(backClick = backClick)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .align(alignment = Alignment.BottomCenter),
            shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp),
            colors = CardDefaults.cardColors(containerColor = backgroundColor1)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp, vertical = 25.dp)
            ) {
                CardTopRows()
                Spacer(modifier = Modifier.height(20.dp))
                GoogleButton(onClick = continueWithGoogleClick)
                Spacer(modifier = Modifier.height(10.dp))
                CreateButton(onClick = createAccountClick)
                Spacer(modifier = Modifier.height(15.dp))
                BottomRow(onClick = loginClick)
            }
        }
    }

}