package com.example.atonce_admin.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.atonce_admin.core.enums.LanguageEnum
import com.example.atonce_admin.core.extensions.applyLanguage
import com.example.atonce_admin.domain.usecase.GetLanguageUseCase
import com.example.atonce_admin.presentation.common.navigation.SetUpNavHost
import com.example.atonce_admin.presentation.common.theme.AtOnceAdminTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AtOnceAdminTheme {
                MainScreen()
            }
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        val lang : GetLanguageUseCase by inject()
        val langCode = if (lang() == LanguageEnum.SYSTEM.apiCode) {
            newBase?.resources?.configuration?.locales?.get(0)?.language
        } else {
            lang()
        }
        val context = langCode?.let { newBase?.applyLanguage(it) }
        super.attachBaseContext(context)
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()

    val snackbarHostState = remember { SnackbarHostState() }

    val background = MaterialTheme.colorScheme.background
    val darkIcons = background.luminance() > 0.5f

    SideEffect {
        systemUiController.setStatusBarColor(
            color = background,
            darkIcons = darkIcons
        )
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(bottom = 50.dp)
            )
        },
        modifier = Modifier.background(background)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .background(background)
        ) {
            SetUpNavHost(navController, snackbarHostState)
        }
    }
}

