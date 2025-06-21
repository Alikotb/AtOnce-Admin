package com.example.atonce_admin.presentation.orders.Component

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce_admin.R
import com.example.atonce_admin.core.enums.OrderStatesEnum
import com.example.atonce_admin.core.extensions.convertNumbersToArabic
import com.example.atonce_admin.core.extensions.replaceEGPWithArabicCurrency
import com.example.atonce_admin.core.extensions.toLocalizedDateTime
import com.example.atonce_admin.domain.entity.OrderEntity
import com.example.atonce_admin.presentation.common.theme.BoldFont
import com.example.atonce_admin.presentation.common.theme.MediumFont
import com.example.atonce_admin.presentation.common.theme.PrimaryColor
import com.example.atonce_admin.presentation.common.theme.RegularFont

@Composable
fun OrderCard(
    order : OrderEntity
) {

    val isDark = isSystemInDarkTheme()
    val elevation = 4.dp
    val containerColor = if (isDark) {
        MaterialTheme.colorScheme.surfaceColorAtElevation(elevation)
    } else {
        MaterialTheme.colorScheme.background
    }

    val type = OrderStatesEnum.fromName(order.orderState)

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
                    text = type.getLocalizedValue(),
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .background(
                            color = type.color,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(4.dp),
                    fontFamily = BoldFont
                )
                Text(text = stringResource(R.string.ord, order.orderId).convertNumbersToArabic() ,
                    fontSize = 14.sp ,
                    color = PrimaryColor,
                    fontFamily = BoldFont
                )
            }
            Text(text = order.createdAt.toLocalizedDateTime().convertNumbersToArabic()
                ,fontSize = 12.sp
            ,fontFamily = MediumFont
                ,color = Color.Gray
            )
            Text(text = order.userName, fontSize = 12.sp , fontFamily = RegularFont)
            Text(text = order.pharmacyName, fontSize = 12.sp ,
                color = PrimaryColor,fontFamily = MediumFont)
            Row(
                modifier = Modifier.fillMaxWidth()
                ,horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = order.warehouseName , fontSize = 12.sp , fontFamily = RegularFont)
                Text(text = "${order.totalPrice} EGP".convertNumbersToArabic().replaceEGPWithArabicCurrency() ,
                    fontSize = 14.sp ,
                    color = PrimaryColor,
                    fontFamily = BoldFont
                )
            }
        }
    }
}
