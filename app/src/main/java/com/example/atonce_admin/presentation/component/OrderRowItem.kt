package com.example.atonce_admin.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce_admin.presentation.theme.MediumFont
import com.example.atonce_admin.presentation.theme.PrimaryColor
import com.example.atonce_admin.presentation.theme.RegularFont
import com.example.atonce_admin.presentation.theme.SemiBoldFont

@Composable
fun OrderRowItem(
    companyName: String,
    newOrdersCount: Int,
    price: String,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 12.dp)
            .clickable { onItemClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ){
            Text(
                text = companyName,
                fontFamily = MediumFont,
                fontSize = 14.sp
            )
            Text(
                text = "$newOrdersCount New orders",
                color = PrimaryColor,
                fontFamily = RegularFont,
                fontSize = 12.sp
            )
        }

        Text(
            text = price,
            fontFamily = MediumFont,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OrderRowItemPreview() {
    OrderRowItem(
        companyName = "Company Name",
        newOrdersCount = 10,
        price = "$100.00"
    )
}

