package com.example.atonce_admin.presentation.users.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce_admin.core.enums.CustomerStateEnum
import com.example.atonce_admin.core.extensions.convertNumbersToArabic
import com.example.atonce_admin.presentation.common.theme.BoldFont
import com.example.atonce_admin.presentation.common.theme.MediumFont
import com.example.atonce_admin.presentation.common.theme.PrimaryColor
import com.example.atonce_admin.presentation.common.theme.RegularFont
import com.example.atonce_admin.presentation.users.model.CustomerModel

@Composable
fun UserCard(
    obj: CustomerModel,
    state: CustomerStateEnum = CustomerStateEnum.ACTIVE,
    onItemClick: (CustomerModel) -> Unit = {}
    ) {

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
            .clickable {
                onItemClick(obj)
            }

    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = obj.userName,
                    fontSize = 14.sp,
                    fontFamily = BoldFont
                )
                Text(
                    text = state.getLocalizedValue(),
                    color = Color.White,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .background(
                            color = state.color,
                            shape = RoundedCornerShape(50.dp)
                        )
                        .padding(horizontal = 8.dp),
                    fontFamily = MediumFont
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null,
                    tint = PrimaryColor,
                    modifier = Modifier.size(16.dp)

                )
                Text(
                    text = obj.phoneNumber.convertNumbersToArabic(), fontSize = 12.sp,
                    fontFamily = MediumFont
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.MedicalServices,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = PrimaryColor

                )
                Text(text = obj.pharmacyName, fontSize = 12.sp, fontFamily = RegularFont)

            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = PrimaryColor,
                    modifier = Modifier.size(16.dp)

                )
                Text(
                    text = "${obj.governorateAndArea} ${obj.address} ",
                    fontSize = 12.sp,
                    fontFamily = RegularFont
                )

            }

        }
    }
}
