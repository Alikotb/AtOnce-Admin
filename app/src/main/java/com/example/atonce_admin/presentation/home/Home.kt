package com.example.atonce_admin.presentation.home

import android.util.Log
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
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.atonce_admin.R
import com.example.atonce_admin.presentation.common.component.CustomTopBar
import com.example.atonce_admin.core.enums.OrderStatesEnum
import com.example.atonce_admin.core.extensions.convertNumbersToArabic
import com.example.atonce_admin.core.extensions.replaceEGPWithArabicCurrency
import com.example.atonce_admin.domain.usecase.GetUserDataUseCase
import com.example.atonce_admin.domain.usecase.SetUserDataUseCase
import com.example.atonce_admin.presentation.home.components.CustomSection
import com.example.atonce_admin.presentation.home.components.DrawerContent
import com.example.atonce_admin.presentation.home.components.OrdersSection
import com.example.atonce_admin.presentation.home.components.PieChartCard
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

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
            title = stringResource(R.string.control_panel),
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
                header = stringResource(R.string.customers_count),
                value = "12".convertNumbersToArabic(),
                textAlign = TextAlign.Center,
                bgColor = background
            )

            val orders = listOf(
                Triple("Hamada Pharma Company", 3, "199 EGP".convertNumbersToArabic().replaceEGPWithArabicCurrency()),
                Triple("Hamada Pharma Company", 3, "199 EGP".convertNumbersToArabic().replaceEGPWithArabicCurrency()),
                Triple("Hamada Pharma Company", 3, "199 EGP".convertNumbersToArabic().replaceEGPWithArabicCurrency())
            )

            OrdersSection(orders = orders, onSeeMoreClick = onSeeMoreClick , onItemClick = onOrdersClicked )

            CustomSection(
                header = stringResource(R.string.revenue),
                value = "10,000 EGP".convertNumbersToArabic().replaceEGPWithArabicCurrency(),
                textAlign = TextAlign.Center,
                bgColor = background
            )


            PieChartCard(
                title = stringResource(R.string.order_status_overview),
                data = listOf(
                    OrderStatesEnum.ORDERED.getLocalizedTitle() to 120,
                    OrderStatesEnum.DELIVERED.getLocalizedTitle() to 30,
                    OrderStatesEnum.CANCELED.getLocalizedTitle() to 150,
                    OrderStatesEnum.RETURNED.getLocalizedTitle() to 10
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
