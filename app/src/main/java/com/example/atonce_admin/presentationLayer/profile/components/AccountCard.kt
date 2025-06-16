package com.example.atonce_admin.presentationLayer.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.atonce_admin.presentationLayer.theme.backgroundColor

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AccountCard(
    name: String,
    subtitle: String,
    onClick: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color.White)
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
                Text(text = name, fontSize = 16.sp, color = Color.Black)
                Text(text = subtitle, fontSize = 14.sp, color = Color(0xFF009688))
            }

            Icon(
                modifier = Modifier.background(color = Color.DarkGray, shape = RoundedCornerShape(size = 8.dp)).padding(4.dp),
                imageVector = Icons.AutoMirrored.Default.ArrowForward,
                contentDescription = "Arrow",
                tint = Color.White
            )
        }
    }
}
