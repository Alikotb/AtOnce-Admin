package com.example.atonce_admin.presentation.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.atonce_admin.presentation.theme.RegularFont

@Composable
fun ProfileListItem(
    icon: ImageVector,
    title: String,
    iconColor: Color = MaterialTheme.colorScheme.onSurface,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
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
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        },
        modifier = Modifier.clickable { onClick() },
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
    Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
}

@Preview(showBackground = true)
@Composable
fun ProfileListItemPreview() {
    ProfileListItem(
        icon = Icons.AutoMirrored.Filled.Logout,
        title = "Profile",
        iconColor = MaterialTheme.colorScheme.error,
        onClick = {}
    )
}
