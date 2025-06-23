package com.example.atonce_admin.presentation.profile.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.atonce_admin.presentation.common.theme.PrimaryColor
import com.example.atonce_admin.presentation.common.theme.RegularFont

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AccountCard(
    name: String,
    subtitle: String,
    onClick: () -> Unit = {}
) {
    val textColor = MaterialTheme.colorScheme.onSurface

    val isDark = isSystemInDarkTheme()
    val elevation = 4.dp
    val containerColor = if (isDark) {
        MaterialTheme.colorScheme.surfaceColorAtElevation(elevation)
    } else {
        MaterialTheme.colorScheme.background
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(elevation)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            GlideImage(
                model = "https://randomuser.me/api/portraits/men/44.jpg",
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    fontSize = 16.sp,
                    color = textColor,
                    fontFamily = RegularFont
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    fontSize = 14.sp,
                    color = PrimaryColor,
                    fontFamily = RegularFont
                )
            }
        }
    }
}
