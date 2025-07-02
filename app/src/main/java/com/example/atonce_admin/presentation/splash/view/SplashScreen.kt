package com.example.atonce_admin.presentation.splash.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.atonce_admin.data.Response
import com.example.atonce_admin.presentation.common.component.LogoIcon
import com.example.atonce_admin.presentation.splash.veiwModel.SplashViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun SplashScreen(
    viewModel: SplashViewModel = koinViewModel(),
    onSplashFinished: (Boolean) -> Unit
) {

    val isLoggedIn = viewModel.isLoggedIn.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.isLoggedIn(2000)
    }
    SplashContent()

    when (isLoggedIn.value) {
        is Response.Error -> {

        }
        Response.Loading -> {

        }
        is Response.Success-> {
            val data = (isLoggedIn.value as Response.Success).data
            onSplashFinished(data)
        }
    }
}


@Preview
@Composable
fun SplashContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
        , contentAlignment = Alignment.Center
    ){
        LogoIcon()
    }
}