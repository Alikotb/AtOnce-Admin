package com.example.atonce_admin.presentation.orders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.atonce_admin.presentation.orders.Component.OrderCard
import com.example.atonce_admin.presentation.common.component.CustomTopBar
import com.example.atonce_admin.core.enums.OrderStatesEnum

@Composable
fun OrdersScreen(
    type : OrderStatesEnum = OrderStatesEnum.ORDERED,
    title: String = "Hamada Pharma",
    onBackClicked: () -> Unit = {}
){

    Column(
        modifier = Modifier.padding(8.dp)
    ){
        CustomTopBar(
            title = title,
            leadingIcon = Icons.AutoMirrored.Default.ArrowBack,
            onLeadingClick = onBackClicked
        )
        LazyColumn {
            items(5) {
                OrderCard(
                    state = type.value,
                    stateColor = type.color
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OrdersScreenPreview() {
    OrdersScreen()
}