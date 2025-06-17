package com.example.atonce_admin.presentationLayer.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce_admin.presentationLayer.component.OrderRowItem
import com.example.atonce_admin.presentationLayer.theme.MediumFont
import com.example.atonce_admin.presentationLayer.theme.RegularFont

@Composable
fun OrdersSection(
    orders: List<Triple<String, Int, String>>,
    onSeeMoreClick: () -> Unit
) {
    Column (
        modifier = Modifier.padding(16.dp)
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ){
            Text(
                text = "New Orders",
                fontSize = 18.sp,
                fontFamily = MediumFont
            )
            Text(
                text = "See more",
                color = Color(0xFF1976D2),
                modifier = Modifier
                    .clickable { onSeeMoreClick() }
                ,fontSize = 14.sp,
                fontFamily = RegularFont
            )
        }
        Column(
            modifier = Modifier.padding(vertical = 8.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .border(1.dp, Color.LightGray.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
        ) {
            orders.forEach {
                Divider(color = Color.LightGray.copy(alpha = 0.2f))
                OrderRowItem(
                    companyName = it.first,
                    newOrdersCount = it.second,
                    price = it.third
                )
            }
        }
    }
}
