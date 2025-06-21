package com.example.atonce_admin.presentation.common.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material3.placeholder
import com.google.accompanist.placeholder.material3.shimmer

@Composable
fun WarehouseRowShimmerItem(
    modifier: Modifier = Modifier
) {

    val isDark = isSystemInDarkTheme()
    val shimmerColor = if (isDark) Color(0xFF444444) else Color(0xFFC0C0C0)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .width(120.dp)
                    .height(16.dp)
                    .placeholder(
                        visible = true,
                        color =  shimmerColor,
                        highlight = PlaceholderHighlight.shimmer()
                    )
            )
            Spacer(
                modifier = Modifier
                    .width(100.dp)
                    .height(14.dp)
                    .placeholder(
                        visible = true,
                        color =  shimmerColor,
                        highlight = PlaceholderHighlight.shimmer()
                    )
            )
        }

        Spacer(
            modifier = Modifier
                .width(60.dp)
                .height(16.dp)
                .placeholder(
                    visible = true,
                    color =  MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
                    highlight = PlaceholderHighlight.shimmer()
                )
        )
    }
}
