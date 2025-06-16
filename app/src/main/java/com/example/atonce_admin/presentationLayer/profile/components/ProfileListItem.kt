package com.example.atonce_admin.presentationLayer.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.atonce_admin.presentationLayer.theme.RegularFont
import com.example.atonce_admin.presentationLayer.theme.backgroundColor

@Composable
fun ProfileListItem(
    icon: ImageVector,
    title: String,
    iconColor: Color = Color.Black,
    textColor: Color = Color.Black,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = {
            Text(title, color = textColor, fontFamily = RegularFont)
        },
        leadingContent = {
            Icon(icon, contentDescription = title, tint = iconColor)
        },
        trailingContent = {
            Icon(Icons.AutoMirrored.Default.KeyboardArrowRight, contentDescription = null)
        },
        modifier = Modifier.clickable { onClick() },
        colors = ListItemDefaults.colors(
            containerColor = backgroundColor
        )
    )
    Divider()
}


@Preview(showBackground = true)
@Composable
fun ProfileListItemPreview() {
    ProfileListItem(
        icon = Icons.AutoMirrored.Default.Logout,
        title = "Profile",
        iconColor = Color.Red,
        onClick = {}
    )
}