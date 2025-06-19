package com.example.atonce_admin.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.atonce_admin.presentation.profile.components.AccountCard
import com.example.atonce_admin.presentation.profile.components.ProfileListItem
import com.example.atonce_admin.presentation.profile.components.ProfileTopBar
import com.example.atonce_admin.presentation.profile.components.QRCodeView
import com.example.atonce_admin.presentation.theme.PrimaryColor

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onLogout: () -> Unit
    ,onBackClicked: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        ProfileTopBar(title = "Profile" , onBackClick = onBackClicked)

        Spacer(modifier = modifier.height(8.dp))

        AccountCard(
            name = "Abdelrahman Kamel",
            subtitle = "01013652874"
        ){

        }

        Spacer(modifier = modifier.height(16.dp))

        QRCodeView(
            data = "Abdo8886",
        )
        Spacer(modifier = modifier.height(32.dp))


        Column (
            modifier = modifier.padding(horizontal = 32.dp)
        ){
            ProfileListItem(
                icon = Icons.Default.HeadsetMic,
                title = "Contact US",
                iconColor = PrimaryColor
            ) { /* contact click */ }

            ProfileListItem(
                icon = Icons.Default.Info,
                title = "About Us"
            ) { /* about click */ }

            ProfileListItem(
                icon = Icons.AutoMirrored.Default.Logout,
                title = "Logout",
                iconColor = Color.Red,
                textColor = Color.Red
            ) { onLogout() }
        }


    }
}


