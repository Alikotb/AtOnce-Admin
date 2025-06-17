package com.example.atonce_admin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.navigation.compose.rememberNavController
import com.example.atonce_admin.presentationLayer.navigation.SetUpNavHost
import com.example.atonce_admin.presentationLayer.theme.AtOnceAdminTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

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
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()

    val background = MaterialTheme.colorScheme.background
    val darkIcons = MaterialTheme.colorScheme.background.luminance() > 0.5f

    SideEffect {
        systemUiController.setStatusBarColor(
            color = background,
            darkIcons = darkIcons
        )
    }

    Scaffold(
        modifier = Modifier.background(background)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .background(background)
        ) {
            SetUpNavHost(navController)
        }
    }
}
