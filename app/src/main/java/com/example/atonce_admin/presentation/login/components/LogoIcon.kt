package com.example.atonce_admin.presentation.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.atonce_admin.presentation.common.theme.PrimaryColor

@Composable
fun LogoIcon(
    modifier: Modifier = Modifier,
    iconColor: Color = Color.White,
    backgroundColor: Color = PrimaryColor
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(backgroundColor)
    ) {
        Icon(
            imageVector = Icons.Default.MedicalServices,
            contentDescription = "Logo Icon",
            tint = iconColor,
            modifier = Modifier.size(48.dp)
        )
    }
}

@Preview
@Composable
fun LogoIconPreview() {
    LogoIcon(
        modifier = Modifier.background(Color.White)
    )
}
