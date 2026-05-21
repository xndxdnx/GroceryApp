package com.example.groceryapp.welcome_login_singup_screens.screens.signup_screen

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.groceryapp.R
import com.example.groceryapp.ui.theme.backgroundColor1
import com.example.groceryapp.ui.theme.whiteColor
import com.example.groceryapp.welcome_login_singup_screens.model.FirebaseViewModel
import com.example.groceryapp.welcome_login_singup_screens.model.UiState
import com.example.groceryapp.welcome_login_singup_screens.screens.signup_screen.components.BottomRowS
import com.example.groceryapp.welcome_login_singup_screens.screens.signup_screen.components.CardTopRowsSignUp
import com.example.groceryapp.welcome_login_singup_screens.screens.signup_screen.components.EmailTextFieldS
import com.example.groceryapp.welcome_login_singup_screens.screens.signup_screen.components.PasswordTextFieldS
import com.example.groceryapp.welcome_login_singup_screens.screens.signup_screen.components.PhoneTextFieldS
import com.example.groceryapp.welcome_login_singup_screens.screens.signup_screen.components.SignUpButtonS
import com.example.groceryapp.welcome_login_singup_screens.screens.signup_screen.components.TopElementsSignUp

@Composable
fun SignUpScreen(
    viewModel: FirebaseViewModel = hiltViewModel(),
    loginClick: () -> Unit = {},
    backClick: () -> Unit = {},
    onSignUpSuccess: () -> Unit = {}
) {

    val context = LocalContext.current

    val email by viewModel.emailInput.collectAsStateWithLifecycle()
    val phone by viewModel.phoneInput.collectAsStateWithLifecycle()
    val password by viewModel.passwordInput.collectAsStateWithLifecycle()

    val userState by viewModel.userState.collectAsStateWithLifecycle()

    LaunchedEffect(userState) {
        when (userState) {
            is UiState.Success -> {
                Toast.makeText(context, "Регистрация успешна!", Toast.LENGTH_SHORT).show()
                onSignUpSuccess() // Уходим на главный экран приложения
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
                item {
                    EmailTextFieldS(
                        value = email,
                        onValueChange = { viewModel.onEmailChange(it) }
                    )
                }
                item { Spacer(modifier = Modifier.height(5.dp)) }
                item {
                    PhoneTextFieldS(
                        value = phone,
                        onValueChange = { viewModel.onPhoneChange(it) }
                    )
                }
                item { Spacer(modifier = Modifier.height(5.dp)) }
                item {
                    PasswordTextFieldS(
                        value = password,
                        onValueChange = { viewModel.onPasswordChange(it) }
                    )
                }
                item { Spacer(modifier = Modifier.height(10.dp)) }
                item {
                    SignUpButtonS(
                        onClick = { viewModel.registerUser(
                            email = email,
                            password = password,
                            phone = phone
                        )}
                    )
                }
                item { Spacer(modifier = Modifier.height(15.dp)) }
                item { BottomRowS(onClick = loginClick) }
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