package com.example.atonce_admin.presentation.orders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.atonce_admin.presentation.common.component.OrderCard
import com.example.atonce_admin.presentation.common.component.CustomTopBar
import com.example.atonce_admin.domain.entity.OrderEntity

@Composable
fun OrdersScreen(
    orders : List<OrderEntity>,
    title: String ,
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
            items(orders){
                OrderCard(
                    order = it
                )
            }
        }
    }
}
