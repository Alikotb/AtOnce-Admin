package com.example.atonce_admin.presentation.profile.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce_admin.R
import com.example.atonce_admin.core.enums.LanguageEnum
import com.example.atonce_admin.presentation.common.theme.MediumFont

@Composable
fun ProfileTopBar(
    title: String,
    onBackClick: () -> Unit = {},
    menuExpanded: Boolean,
    onDismissMenu: () -> Unit,
    onLanguageSelected: (LanguageEnum) -> Unit
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

        Box(modifier = Modifier.align(Alignment.CenterEnd)) {
            IconButton(
                onClick = { onDismissMenu() }
            ) {
                Icon(Icons.Default.Language, contentDescription = "Language")
            }

            DropdownMenu(
                expanded = menuExpanded,
                onDismissRequest = { onDismissMenu() },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(12.dp))
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
                            onDismissMenu()
                        },
                        leadingIcon = {
                            val icon = when (language) {
                                LanguageEnum.ENGLISH -> painterResource(R.drawable.english)
                                LanguageEnum.ARABIC -> painterResource(R.drawable.arabic)
                                LanguageEnum.SYSTEM -> painterResource(R.drawable.system)
                            }
                            Icon(icon, contentDescription = null, modifier = Modifier.size(24.dp), tint = androidx.compose.ui.graphics.Color.Unspecified)
                        }
                    )
                }
            }
        }
    }
}


