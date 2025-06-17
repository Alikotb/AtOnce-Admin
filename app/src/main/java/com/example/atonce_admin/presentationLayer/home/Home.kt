package com.example.atonce_admin.presentationLayer.home

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.atonce_admin.presentationLayer.component.CustomTopBar
import com.example.atonce_admin.presentationLayer.home.components.CustomSection
import com.example.atonce_admin.presentationLayer.home.components.OrdersSection
import com.example.atonce_admin.presentationLayer.home.components.PieChartCard
import com.example.atonce_admin.presentationLayer.theme.backgroundColor

@Composable
fun HomeScreen(
    onDrawerClicked: () -> Unit,
    onProfileClicked: () -> Unit,
    onSeeMoreClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {

        CustomTopBar(
            title = "Control Panel",
            leadingIcon = Icons.Default.Menu,
            onLeadingClick = onDrawerClicked,
            trailingIcon = Icons.Default.Person,
            onTrailingClick = onProfileClicked
        )

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {


            CustomSection(
                header = "Customers Count",
                value = "12",
                textAlign = TextAlign.Center,
                bgColor = backgroundColor
            )

            val orders = listOf(
                Triple("Hamada Pharma Company", 3, "199 EGP"),
                Triple("Hamada Pharma Company", 3, "199 EGP"),
                Triple("Hamada Pharma Company", 3, "199 EGP")
            )

            OrdersSection(orders = orders, onSeeMoreClick = onSeeMoreClick)

            CustomSection(
                header = "Revenue",
                value = "10,000 EGP",
                textAlign = TextAlign.Center,
                bgColor = backgroundColor
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

            Spacer(Modifier.height(160.dp))

        }
    }
}