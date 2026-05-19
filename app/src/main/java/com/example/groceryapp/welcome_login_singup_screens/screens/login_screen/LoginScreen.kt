package com.example.groceryapp.welcome_login_singup_screens.screens.login_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.groceryapp.R
import com.example.groceryapp.ui.theme.backgroundColor1
import com.example.groceryapp.ui.theme.whiteColor
import com.example.groceryapp.welcome_login_singup_screens.screens.create_account_screen.components.BottomRowS
import com.example.groceryapp.welcome_login_singup_screens.screens.create_account_screen.components.CardTopRowsSignUp
import com.example.groceryapp.welcome_login_singup_screens.screens.create_account_screen.components.EmailTextFieldS
import com.example.groceryapp.welcome_login_singup_screens.screens.create_account_screen.components.PasswordTextFieldS
import com.example.groceryapp.welcome_login_singup_screens.screens.create_account_screen.components.PhoneTextFieldS
import com.example.groceryapp.welcome_login_singup_screens.screens.create_account_screen.components.SignUpButtonS
import com.example.groceryapp.welcome_login_singup_screens.screens.create_account_screen.components.TopElementsSignUp
import com.example.groceryapp.welcome_login_singup_screens.screens.login_screen.components.BottomRowL
import com.example.groceryapp.welcome_login_singup_screens.screens.login_screen.components.CardTopRowsL
import com.example.groceryapp.welcome_login_singup_screens.screens.login_screen.components.EmailTextFieldL
import com.example.groceryapp.welcome_login_singup_screens.screens.login_screen.components.LoginButtonL
import com.example.groceryapp.welcome_login_singup_screens.screens.login_screen.components.PasswordTextFieldL
import com.example.groceryapp.welcome_login_singup_screens.screens.login_screen.components.RememberRow

@Composable
fun LoginScreen(
    rememberMeClick: () -> Unit = {},
    forgotPasswordClick: () -> Unit = {},
    signUpClick: () -> Unit = {},
    backClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = whiteColor),
    ) {
        Image(
            painter = painterResource(R.drawable.login_picture),
            contentDescription = null,
        )
        TopElementsSignUp(backClick = backClick)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(430.dp)
                .align(alignment = Alignment.BottomCenter),
            shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp),
            colors = CardDefaults.cardColors(containerColor = backgroundColor1)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp, vertical = 25.dp)
            ) {
                item { CardTopRowsL() }
                item { Spacer(modifier = Modifier.height(20.dp)) }
                item { EmailTextFieldL() }
                item { Spacer(modifier = Modifier.height(5.dp)) }
                item { PasswordTextFieldL() }
                item { Spacer(modifier = Modifier.height(8.dp)) }
                item { RememberRow() }
                item { Spacer(modifier = Modifier.height(8.dp)) }
                item { LoginButtonL() }
                item { Spacer(modifier = Modifier.height(15.dp)) }
                item { BottomRowL(onClick = signUpClick) }


            }
        }
    }

}