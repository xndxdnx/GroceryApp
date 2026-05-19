package com.example.groceryapp.welcome_login_singup_screens.screens.create_account_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
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
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.groceryapp.ui.theme.blackColor
import com.example.groceryapp.ui.theme.primaryDarkColor
import com.example.groceryapp.ui.theme.whiteColor
import com.example.groceryapp.welcome_login_singup_screens.screens.create_account_screen.components.BottomRowS
import com.example.groceryapp.welcome_login_singup_screens.screens.create_account_screen.components.CardTopRowsSignUp
import com.example.groceryapp.welcome_login_singup_screens.screens.create_account_screen.components.EmailTextFieldS
import com.example.groceryapp.welcome_login_singup_screens.screens.create_account_screen.components.PasswordTextFieldS
import com.example.groceryapp.welcome_login_singup_screens.screens.create_account_screen.components.PhoneTextFieldS
import com.example.groceryapp.welcome_login_singup_screens.screens.create_account_screen.components.SignUpButtonS
import com.example.groceryapp.welcome_login_singup_screens.screens.create_account_screen.components.TopElementsSignUp

@Composable
@Preview
fun CreateAccountScreen(
    signUpClick: () -> Unit = {},
    loginClick: () -> Unit = {},
    backClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = whiteColor),
    ) {
        Image(
            painter = painterResource(R.drawable.sign_up_picture),
            contentDescription = null,
        )
        TopElementsSignUp(backClick = backClick)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(440.dp)
                .align(alignment = Alignment.BottomCenter),
            shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp),
            colors = CardDefaults.cardColors(containerColor = backgroundColor1)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp, vertical = 25.dp)
            ) {
                item { CardTopRowsSignUp() }
                item { Spacer(modifier = Modifier.height(20.dp)) }
                item { EmailTextFieldS() }
                item { Spacer(modifier = Modifier.height(5.dp)) }
                item { PhoneTextFieldS() }
                item { Spacer(modifier = Modifier.height(5.dp)) }
                item { PasswordTextFieldS() }
                item { Spacer(modifier = Modifier.height(10.dp)) }
                item { SignUpButtonS() }
                item { Spacer(modifier = Modifier.height(15.dp)) }
                item { BottomRowS(onClick = loginClick) }


            }
        }
    }

}