package com.example.groceryapp.welcome_login_singup_screens.screens.login_screen

import android.widget.Toast
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.groceryapp.R
import com.example.groceryapp.ui.theme.backgroundColor1
import com.example.groceryapp.ui.theme.whiteColor
import com.example.groceryapp.welcome_login_singup_screens.model.FirebaseViewModel
import com.example.groceryapp.welcome_login_singup_screens.model.UiState
import com.example.groceryapp.welcome_login_singup_screens.screens.signup_screen.components.TopElementsSignUp
import com.example.groceryapp.welcome_login_singup_screens.screens.login_screen.components.BottomRowL
import com.example.groceryapp.welcome_login_singup_screens.screens.login_screen.components.CardTopRowsL
import com.example.groceryapp.welcome_login_singup_screens.screens.login_screen.components.LoginButtonL
import com.example.groceryapp.welcome_login_singup_screens.screens.login_screen.components.RememberRow
import com.example.groceryapp.welcome_login_singup_screens.screens.signup_screen.components.EmailTextFieldL
import com.example.groceryapp.welcome_login_singup_screens.screens.signup_screen.components.PasswordTextFieldL

@Composable
fun LoginScreen(
    viewModel: FirebaseViewModel = hiltViewModel(),
    loginClick: () -> Unit = {},
    rememberMeClick: () -> Unit = {},
    forgotPasswordClick: () -> Unit = {},
    signUpClick: () -> Unit = {},
    backClick: () -> Unit = {},
    onSignInSuccess: () -> Unit = {}
) {

    val context = LocalContext.current
    val email by viewModel.emailInput.collectAsStateWithLifecycle()
    val password by viewModel.passwordInput.collectAsStateWithLifecycle()
    val userState by viewModel.userState.collectAsStateWithLifecycle()

    LaunchedEffect(userState) {
        when (userState) {
            is UiState.Success -> {
                Toast.makeText(context, "Успешный вход!", Toast.LENGTH_SHORT).show()
                onSignInSuccess() // Уходим на главный экран приложения
            }

            is UiState.Error -> {
                val errorMessage = (userState as UiState.Error).message
                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
            }
            else -> {}
        }
    }
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
                item {
                    EmailTextFieldL(
                        value = email,
                        onValueChange = { viewModel.onEmailChange(it) }
                    )
                }
                item { Spacer(modifier = Modifier.height(5.dp)) }
                item {
                    PasswordTextFieldL(
                        value = password,
                        onValueChange = { viewModel.onPasswordChange(it) }
                    )
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }
                item { RememberRow(onForgotClick = {
                    viewModel.uploadAvatar(R.drawable.login_picture, context = context)
                }) }
                item { Spacer(modifier = Modifier.height(8.dp)) }
                item { LoginButtonL(
                    onClick = {
                        viewModel.signInUser(
                            email = email,
                            password = password
                        )
                    }
                ) }
                item { Spacer(modifier = Modifier.height(15.dp)) }
                item { BottomRowL(onClick = signUpClick) }
            }
        }
        if (userState is UiState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }

}