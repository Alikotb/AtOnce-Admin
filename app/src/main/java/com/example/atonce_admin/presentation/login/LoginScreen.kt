package com.example.atonce_admin.presentation.login


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce_admin.R
import com.example.atonce_admin.presentation.common.theme.BoldFont
import com.example.atonce_admin.presentation.common.theme.PrimaryColor
import com.example.atonce_admin.presentation.common.theme.RegularFont
import com.example.atonce_admin.presentation.login.components.CustomTextField
import com.example.atonce_admin.presentation.login.components.LogoIcon
import com.example.atonce_admin.presentation.login.components.PrimaryButton
import com.example.atonce_admin.presentation.login.viemodel.LoginViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    onLoginClick: () -> Unit = {},
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.uiState.collect { msg ->
            if (msg == "Login successful.") {
                onLoginClick()
            } else {
                snackbarHostState.showSnackbar(
                    message = msg,
                    duration = SnackbarDuration.Long
                )
            }
        }
    }



    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        LogoIcon()

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            stringResource(R.string.welcome_back), fontSize = 20.sp, color = PrimaryColor,
            fontFamily = BoldFont
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            stringResource(R.string.to_continue_login_now),
            fontSize = 12.sp,
            fontFamily = RegularFont
        )

        Spacer(modifier = Modifier.height(32.dp))

        CustomTextField(
            label = stringResource(R.string.email),
            value = email,
            onValueChange = { email = it })
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            label = stringResource(R.string.password),
            value = password,
            onValueChange = { password = it },
            isPassword = true
        )

        Spacer(modifier = Modifier.height(32.dp))
        PrimaryButton(text = stringResource(R.string.login)) {
            viewModel.login(email, password)
        }
    }
}

