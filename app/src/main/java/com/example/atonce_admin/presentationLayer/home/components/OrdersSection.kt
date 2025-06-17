package com.example.atonce_admin.presentationLayer.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce_admin.presentationLayer.component.OrderRowItem
import com.example.atonce_admin.presentationLayer.theme.MediumFont
import com.example.atonce_admin.presentationLayer.theme.RegularFont

@Composable
fun OrdersSection(
    orders: List<Triple<String, Int, String>>,
    onItemClick: () -> Unit = {},
    onSeeMoreClick: () -> Unit
) {
    val containerColor = MaterialTheme.colorScheme.surface
    val borderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
    val dividerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f)
    val seeMoreColor = MaterialTheme.colorScheme.primary

    Column(modifier = Modifier.padding(16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "New Orders",
                fontSize = 18.sp,
                fontFamily = MediumFont
            )
            Text(
                text = "See more",
                color = seeMoreColor,
                modifier = Modifier.clickable { onSeeMoreClick() },
                fontSize = 14.sp,
                fontFamily = RegularFont
            )
        }

        Column(
            modifier = Modifier
                .padding(top = 8.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(containerColor)
                .border(1.dp, borderColor, RoundedCornerShape(12.dp))
        ) {
            orders.forEachIndexed { index, order ->
                if (index > 0) {
                    Divider(color = dividerColor)
                }
                OrderRowItem(
                    companyName = order.first,
                    newOrdersCount = order.second,
                    price = order.third,
                    onItemClick = onItemClick
                )
            }
        }
    }
}
