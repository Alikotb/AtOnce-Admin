package com.example.atonce_admin.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.atonce_admin.presentation.component.CustomTopBar
import com.example.atonce_admin.presentation.enums.OrderStatesEnum
import com.example.atonce_admin.presentation.home.components.CustomSection
import com.example.atonce_admin.presentation.home.components.DrawerContent
import com.example.atonce_admin.presentation.home.components.OrdersSection
import com.example.atonce_admin.presentation.home.components.PieChartCard
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    onDrawerClicked: () -> Unit,
    onProfileClicked: () -> Unit,
    onOrdersClicked: () -> Unit,
    onSeeMoreClick: () -> Unit
) {

    val background = MaterialTheme.colorScheme.background

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {

        CustomTopBar(
            title = "Control Panel",
            leadingIcon = Icons.Default.Menu,
            onLeadingClick = onDrawerClicked,
            trailingIcon = Icons.Outlined.AccountCircle,
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
                bgColor = background
            )

            val orders = listOf(
                Triple("Hamada Pharma Company", 3, "199 EGP"),
                Triple("Hamada Pharma Company", 3, "199 EGP"),
                Triple("Hamada Pharma Company", 3, "199 EGP")
            )

            OrdersSection(orders = orders, onSeeMoreClick = onSeeMoreClick , onItemClick = onOrdersClicked )

            CustomSection(
                header = "Revenue",
                value = "10,000 EGP",
                textAlign = TextAlign.Center,
                bgColor = background
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

@Composable
fun HomeWithDrawerScreen(
    onProfileClicked: () -> Unit,
    onCustomerClicked: () -> Unit,
    onOrdersClicked: () -> Unit,
    onSeeMoreClick: () -> Unit,
    onLogout: () -> Unit,
    onItemClicked: (OrderStatesEnum) -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.8f)
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                DrawerContent(
                    onItemClick = {
                        title -> onItemClicked(title)
                        scope.launch { drawerState.close() }

                    },
                    onLogout = {
                        onLogout()
                        scope.launch { drawerState.close() }
                    },
                    onProfileClicked = {
                        onProfileClicked()
                        scope.launch { drawerState.close() }
                    },
                    onCustomerClicked = {
                        onCustomerClicked()
                        scope.launch { drawerState.close() }
                    }
                )
            }
        }
    ) {
        HomeScreen(
            onDrawerClicked = { scope.launch { drawerState.open() } },
            onProfileClicked = onProfileClicked,
            onOrdersClicked = onOrdersClicked,
            onSeeMoreClick = onSeeMoreClick
        )
    }
}
