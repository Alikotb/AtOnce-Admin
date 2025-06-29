package com.example.atonce_admin.presentation.blogger

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.atonce_admin.core.constants.AppConstants
import com.example.atonce_admin.presentation.blogger.component.WebViewContainer
import com.example.atonce_admin.presentation.common.component.CustomTopBar
import com.example.atonce_admin.presentation.common.component.LoadingIndicatorView

@Composable
fun BloggerScreen(
    title: String = "Contact Us",
    url: String = AppConstants.CONTACT_US_LINK,
    onBackClicked: () -> Unit = {},
) {
    var isLoadingState by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CustomTopBar(
            title = title,
            leadingIcon = Icons.AutoMirrored.Filled.ArrowBack,
            onLeadingClick = onBackClicked,
        )

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            WebViewContainer(
                url = url,
                isLoadingState = { isLoadingState = it }
            )
            if (isLoadingState) {
                LoadingIndicatorView()
            }

        }
    }
}
