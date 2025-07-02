package com.example.atonce_admin.presentation.profile.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce_admin.R
import com.example.atonce_admin.core.enums.LanguageEnum

@Composable
fun LanguageDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    onLanguageSelected: (LanguageEnum) -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .clip(RoundedCornerShape(12.dp))

    ) {
        LanguageEnum.entries.forEach { language ->
            DropdownMenuItem(
                text = {
                    Text(
                        language.getDisplayName(language),
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                onClick = {
                    onLanguageSelected(language)
                    onDismissRequest()
                },
                leadingIcon = {
                    val icon = when (language) {
                        LanguageEnum.ENGLISH -> painterResource(R.drawable.uk)
                        LanguageEnum.ARABIC -> painterResource(R.drawable.egypt)
                        LanguageEnum.SYSTEM -> painterResource(R.drawable.system)
                    }
                    Icon(
                        painter = icon,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = androidx.compose.ui.graphics.Color.Unspecified
                    )
                }
            )
        }
    }
}
