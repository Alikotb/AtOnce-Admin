package com.example.atonce_admin.presentation.common.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce_admin.R
import com.example.atonce_admin.core.extensions.convertNumbersToArabic
import com.example.atonce_admin.presentation.common.theme.MediumFont
import com.example.atonce_admin.presentation.common.theme.PrimaryColor
import com.example.atonce_admin.presentation.common.theme.RegularFont

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
                text = stringResource(R.string.orders_count, newOrdersCount),
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

