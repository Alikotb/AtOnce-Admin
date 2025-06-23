package com.example.atonce_admin.presentation.profile.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.atonce_admin.R
import com.example.atonce_admin.core.constants.AppConstants
import com.example.atonce_admin.core.enums.LanguageEnum
import com.example.atonce_admin.core.extensions.convertNumbersToArabic
import com.example.atonce_admin.core.extensions.restartActivity
import com.example.atonce_admin.core.utils.restartActivity
import com.example.atonce_admin.presentation.common.component.AppDialog
import com.example.atonce_admin.presentation.profile.view.components.AccountCard
import com.example.atonce_admin.presentation.profile.view.components.ProfileListItem
import com.example.atonce_admin.presentation.profile.view.components.ProfileTopBar
import com.example.atonce_admin.presentation.profile.view.components.QRCodeView
import com.example.atonce_admin.presentation.common.theme.PrimaryColor
import com.example.atonce_admin.presentation.profile.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel(),
    onLogout: () -> Unit,
    onContactClicked: (String , String) -> Unit
    , onBackClicked: () -> Unit
) {
    val context = LocalContext.current
    val user = viewModel.userData

    var menuExpanded by remember { mutableStateOf(false) }
    var showAboutDialog by remember { mutableStateOf(false) }
    var showLogoutDialog by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxSize()) {
        ProfileTopBar(
            title = stringResource(R.string.profile),
            onBackClick = onBackClicked,
            menuExpanded = menuExpanded,
            onDismissMenu = { menuExpanded = !menuExpanded },
            onLanguageSelected = {
                viewModel.setLanguage(it)
                context.restartActivity()
            }
        )

        Spacer(modifier = modifier.height(8.dp))

        AccountCard(
            name = user.name,
            subtitle = user.phone.convertNumbersToArabic()
        ){

        }

        Spacer(modifier = modifier.height(16.dp))

        QRCodeView(
            data = user.code,
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
            ) {
                showAboutDialog = true
            }

            if (showAboutDialog) {
                AppDialog(
                    title = stringResource(R.string.about_us),
                    message = "This is the About Us section for the app. You can put any info here.",
                    onDismiss = { showAboutDialog = false }
                )
            }

            ProfileListItem(
                icon = Icons.AutoMirrored.Default.Logout,
                title = stringResource(R.string.logout),
                iconColor = Color.Red,
                textColor = Color.Red
            ) {
                showLogoutDialog = true
            }

            if (showLogoutDialog) {
                AppDialog(
                    title = stringResource(R.string.logout),
                    message = stringResource(R.string.are_you_sure_you_want_to_logout),
                    confirmText = stringResource(R.string.logout)
                ) {
                    showLogoutDialog = false
                    onLogout()
                }
            }
        }
    }
}


