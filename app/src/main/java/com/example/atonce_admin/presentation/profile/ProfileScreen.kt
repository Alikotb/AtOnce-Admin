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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.atonce_admin.R
import com.example.atonce_admin.core.constants.AppConstants
import com.example.atonce_admin.core.extensions.convertNumbersToArabic
import com.example.atonce_admin.presentation.profile.components.AccountCard
import com.example.atonce_admin.presentation.profile.components.ProfileListItem
import com.example.atonce_admin.presentation.profile.components.ProfileTopBar
import com.example.atonce_admin.presentation.profile.components.QRCodeView
import com.example.atonce_admin.presentation.common.theme.PrimaryColor

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onLogout: () -> Unit,
    onContactClicked: (String , String) -> Unit
    ,onBackClicked: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        ProfileTopBar(title = stringResource(R.string.profile), onBackClick = onBackClicked)

        Spacer(modifier = modifier.height(8.dp))

        AccountCard(
            name = "Abdelrahman Kamel",
            subtitle = "01013652874".convertNumbersToArabic()
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

            val contactTitle = stringResource(R.string.contact_us)


            ProfileListItem(
                icon = Icons.Default.HeadsetMic,
                title = contactTitle,
                iconColor = PrimaryColor
            ) { onContactClicked(contactTitle, AppConstants.CONTACT_US_LINK) }

            ProfileListItem(
                icon = Icons.Default.Info,
                title = stringResource(R.string.about_us)
            ) { /* about click */ }

            ProfileListItem(
                icon = Icons.AutoMirrored.Default.Logout,
                title = stringResource(R.string.logout),
                iconColor = Color.Red,
                textColor = Color.Red
            ) { onLogout() }
        }


    }
}


