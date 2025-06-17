package com.example.atonce_admin.presentationLayer.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.atonce_admin.presentationLayer.component.CustomTopBar
import com.example.atonce_admin.presentationLayer.home.components.PieChartCard

@Composable
fun HomeScreen(
    onDrawerClicked: () -> Unit,
    onProfileClicked: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ){
        CustomTopBar(
            title = "Control Panel",
            leadingIcon = Icons.Default.Menu,
            onLeadingClick = onDrawerClicked,
            trailingIcon = Icons.Default.Person,
            onTrailingClick = onProfileClicked
        )

        PieChartCard(
            title = "Order Status OverView",
            data = listOf(
                "New Orders" to 120,
                "Delivered" to 30,
                "Canceled" to 150,
                "Returned" to 10
            )
        )

    }
}