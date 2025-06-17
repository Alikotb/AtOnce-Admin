package com.example.atonce_admin.presentationLayer.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce_admin.presentationLayer.theme.MediumFont

@Composable
fun CustomTopBar(
    title: String,
    leadingIcon: ImageVector? = null,
    onLeadingClick: (() -> Unit)? = null,
    trailingIcon: ImageVector? = null,
    onTrailingClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leadingIcon != null && onLeadingClick != null) {
            IconButton(onClick = onLeadingClick) {
                Icon(imageVector = leadingIcon, contentDescription = "Leading Icon")
            }
        } else {
            Spacer(modifier = Modifier.width(16.dp))
        }

        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontFamily = MediumFont,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        if (trailingIcon != null && onTrailingClick != null) {
            IconButton(onClick = onTrailingClick) {
                Icon(imageVector = trailingIcon, contentDescription = "Trailing Icon")
            }
        } else {
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}
