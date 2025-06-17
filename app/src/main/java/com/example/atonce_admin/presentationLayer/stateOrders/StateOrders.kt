package com.example.atonce_admin.presentationLayer.stateOrders

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.atonce_admin.presentationLayer.component.CustomTopBar
import com.example.atonce_admin.presentationLayer.component.OrderRowItem


@Preview
@Composable
fun StateOrders(
    orders: List<Triple<String, Int, String>> = listOf(
        Triple("Hamada Pharma Company", 3, "199 EGP"),
        Triple("Hamada Pharma Company", 3, "199 EGP"),
        Triple("Hamada Pharma Company", 3, "199 EGP")
    ),
    title : String = "New Orders",
    onBackClicked: () -> Unit = {},

){

    CustomTopBar(
        title = title,
        leadingIcon = Icons.Default.Menu,
        onLeadingClick = onBackClicked,
    )

    LazyColumn(){
        items(items = orders){
            OrderRowItem(
                companyName = it.first,
                newOrdersCount = it.second,
                price = it.third
            )
        }
    }




}