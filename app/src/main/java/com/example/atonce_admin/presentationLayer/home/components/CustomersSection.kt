package com.example.atonce_admin.presentationLayer.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce_admin.presentationLayer.theme.*
import androidx.compose.foundation.isSystemInDarkTheme

@Composable
fun CustomSection(
    header: String = "Customers Count",
    value: String = "12",
    textAlign: TextAlign = TextAlign.Center,
    bgColor: Color = backgroundColor
) {
    val isDark = isSystemInDarkTheme()
    val elevation = 4.dp
    val containerColor = if (isDark) {
        MaterialTheme.colorScheme.surfaceColorAtElevation(elevation)
    } else {
        bgColor
    }

    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = header,
                fontSize = 18.sp,
                fontFamily = MediumFont
            )
            Text(
                text = value,
                fontSize = 24.sp,
                fontFamily = BoldFont,
                color = PrimaryColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = textAlign
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomSection() {
    AtOnceAdminTheme {
        CustomSection()
    }
}
