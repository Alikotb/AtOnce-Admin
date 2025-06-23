package com.example.atonce_admin.presentation.profile.view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce_admin.presentation.common.theme.MediumFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar(
    title: String,
    onBackClick: () -> Unit = {},
    onGlobeClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontFamily = MediumFont,
            modifier = Modifier.align(Alignment.Center)
        )

        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }

        IconButton(
            onClick = onGlobeClick,
            modifier = Modifier
                .align(Alignment.CenterEnd)
        ) {
            Icon(Icons.Default.Language, contentDescription = "Language")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileTopBarPreview() {
    ProfileTopBar(title = "Profile")
}

