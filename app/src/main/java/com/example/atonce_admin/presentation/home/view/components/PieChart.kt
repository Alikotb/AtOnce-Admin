package com.example.atonce_admin.presentation.home.view.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce_admin.core.extensions.convertNumbersToArabic
import com.example.atonce_admin.presentation.common.theme.AtOnceAdminTheme
import com.example.atonce_admin.presentation.common.theme.RegularFont
import com.example.atonce_admin.presentation.common.theme.SemiBoldFont
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material3.placeholder
import com.google.accompanist.placeholder.material3.shimmer

@Composable
fun PieChartCard(
    title: String,
    data: List<Pair<String, Int>>,
    colors: List<Color> = listOf(
        Color(0xFF2196F3),
        Color(0xFF4CAF50),
        Color(0xFFE91E63),
        Color(0xFF9C27B0)
    ),
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
) {
    val isDark = isSystemInDarkTheme()
    val elevation = 4.dp
    val cardColor = if (!isDark) {
        Color.White
    } else {
        MaterialTheme.colorScheme.surfaceColorAtElevation(elevation)
    }

    Column(modifier = modifier) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontFamily = SemiBoldFont,
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
        )

        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = cardColor),
            elevation = CardDefaults.cardElevation(defaultElevation = elevation),
            modifier = Modifier.fillMaxWidth()
        ) {
            val total = data.sumOf { it.second }.takeIf { it > 0 } ?: 1
            val proportions = data.map { it.second.toFloat() / total }
            val sweepAngles = proportions.map { it * 360f }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Canvas(
                    modifier = Modifier.size(140.dp)
                ) {
                    var startAngle = -90f
                    sweepAngles.forEachIndexed { index, sweepAngle ->
                        drawArc(
                            color = colors.getOrElse(index) { Color.Gray },
                            startAngle = startAngle,
                            sweepAngle = sweepAngle,
                            useCenter = true
                        )
                        startAngle += sweepAngle
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    data.forEachIndexed { index, item ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .background(
                                        colors.getOrElse(index) { Color.Gray },
                                        shape = CircleShape
                                    )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "${item.first}  ${item.second}".convertNumbersToArabic(),
                                fontFamily = RegularFont,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PieChartCardPreview() {
    AtOnceAdminTheme {
        val sampleData = listOf(
            "New Orders" to 120,
            "Delivered" to 30,
            "Canceled" to 150,
            "Returned" to 10
        )
        PieChartCard(title = "Orders Summary", data = sampleData)
    }
}


@Composable
fun PieChartCardShimmer(
    modifier: Modifier = Modifier
) {
    val isDark = isSystemInDarkTheme()
    val shimmerColor = if (isDark) Color(0xFF444444) else Color(0xFFC0C0C0)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Spacer(
            modifier = Modifier
                .width(120.dp)
                .height(20.dp)
                .placeholder(
                    visible = true,
                    color = shimmerColor,
                    highlight = PlaceholderHighlight.shimmer()
                )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .size(140.dp)
                    .placeholder(
                        visible = true,
                        color = shimmerColor,
                        highlight = PlaceholderHighlight.shimmer()
                    )
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(4) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacer(
                            modifier = Modifier
                                .size(10.dp)
                                .placeholder(
                                    visible = true,
                                    color = shimmerColor,
                                    highlight = PlaceholderHighlight.shimmer()
                                )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Spacer(
                            modifier = Modifier
                                .width(100.dp)
                                .height(14.dp)
                                .placeholder(
                                    visible = true,
                                    color = shimmerColor,
                                    highlight = PlaceholderHighlight.shimmer()
                                )
                        )
                    }
                }
            }
        }
    }
}
