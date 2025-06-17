package com.example.atonce_admin.presentationLayer.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = companyName,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
            )
            Text(
                text = "$newOrdersCount New orders",
                style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFF009688))
            )
        }

        Text(
            text = price,
            style = MaterialTheme.typography.bodyMedium
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

