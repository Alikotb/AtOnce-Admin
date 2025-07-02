package com.example.atonce_admin.presentation.users.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

@Composable
fun UserCardShimmer() {
    val isDark = isSystemInDarkTheme()
    val elevation = 4.dp
    val containerColor = if (isDark) {
        MaterialTheme.colorScheme.surfaceColorAtElevation(elevation)
    } else {
        MaterialTheme.colorScheme.background
    }

    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .height(16.dp)
                        .width(120.dp)
                        .placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.shimmer( highlightColor = Color.White),
                            color = Color.LightGray,
                            shape = RoundedCornerShape(4.dp)
                        )
                )
                Box(
                    modifier = Modifier
                        .height(16.dp)
                        .width(60.dp)
                        .background(Color.Gray, shape = RoundedCornerShape(50.dp))
                        .placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.shimmer( highlightColor = Color.White),
                            color = Color.LightGray,
                            shape = RoundedCornerShape(50.dp)
                        )
                )
            }

            repeat(3) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .placeholder(
                                visible = true,
                                highlight = PlaceholderHighlight.shimmer( highlightColor = Color.White),
                                color = Color.LightGray,
                                shape = CircleShape
                            )
                    )
                    Box(
                        modifier = Modifier
                            .height(12.dp)
                            .fillMaxWidth(0.7f)
                            .placeholder(
                                visible = true,
                                highlight = PlaceholderHighlight.shimmer( highlightColor = Color.White),
                                color = Color.LightGray,
                                shape = RoundedCornerShape(4.dp)
                            )
                    )
                }
            }
        }
    }
}
