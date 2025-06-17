package com.example.atonce_admin.presentationLayer.Orders.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce_admin.presentationLayer.theme.BoldFont
import com.example.atonce_admin.presentationLayer.theme.MediumFont
import com.example.atonce_admin.presentationLayer.theme.PrimaryColor
import com.example.atonce_admin.presentationLayer.theme.RegularFont

@Composable
fun OrderCard(
    state: String = "Cancelled",
    stateColor: Color = Color.Red,
    date: String = "June 12, 2025 - 15:36 PM",
    orderNumber: String = "#ORD-12345",
    user: String = "Luis Antonio Valencia",
    pharmacy: String = "Manchester Pharmacy",
    address: String = "123 Main Street, City, Country",
    price: String = "630.00 EGP",


) {

    val isDark = isSystemInDarkTheme()
    val elevation = 4.dp
    val containerColor = if (isDark) {
        MaterialTheme.colorScheme.surfaceColorAtElevation(elevation)
    } else {
        MaterialTheme.colorScheme.background
    }
    Card(
        shape = RoundedCornerShape(8.dp)
        ,colors = CardDefaults.cardColors(containerColor = containerColor)
        ,elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)

    ){
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = state,
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .background(
                            color = stateColor,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(4.dp),
                    fontFamily = MediumFont
                )
                Text(text = orderNumber ,
                    fontSize = 14.sp ,
                    color = PrimaryColor,
                    fontFamily = BoldFont
                )
            }
            Text(text = date
                ,fontSize = 12.sp
            ,fontFamily = MediumFont
            )
            Text(text = user, fontSize = 12.sp , fontFamily = RegularFont)
            Text(text = pharmacy, fontSize = 12.sp ,
                color = PrimaryColor,fontFamily = MediumFont)
            Row(
                modifier = Modifier.fillMaxWidth()
                ,horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = address , fontSize = 12.sp , fontFamily = RegularFont)
                Text(text = price ,
                    fontSize = 14.sp ,
                    color = PrimaryColor,
                    fontFamily = BoldFont
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OrderCardPreview() {
    OrderCard()
}