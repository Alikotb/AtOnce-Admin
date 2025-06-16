package com.example.atonce_admin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.atonce_admin.presentationLayer.login.LoginScreen
import com.example.atonce_admin.presentationLayer.profile.ProfileScreen
import com.example.atonce_admin.presentationLayer.theme.backgroundColor


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainActivityPreview()
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun MainActivityPreview() {
        Scaffold(
            modifier = Modifier.background(backgroundColor)
        )
        {
            innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(backgroundColor)
            ){
               ProfileScreen()

            }


        }
    }

}

