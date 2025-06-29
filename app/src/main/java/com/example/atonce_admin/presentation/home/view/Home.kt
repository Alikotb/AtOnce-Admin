package com.example.atonce_admin.presentation.home.view

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.atonce_admin.R
import com.example.atonce_admin.core.enums.OrderStatesEnum
import com.example.atonce_admin.core.extensions.convertNumbersToArabic
import com.example.atonce_admin.core.extensions.replaceEGPWithArabicCurrency
import com.example.atonce_admin.data.Response
import com.example.atonce_admin.domain.entity.OrderEntity
import com.example.atonce_admin.presentation.common.component.CustomTopBar
import com.example.atonce_admin.presentation.common.component.ErrorView
import com.example.atonce_admin.presentation.home.view.components.CustomSection
import com.example.atonce_admin.presentation.home.view.components.CustomSectionShimmer
import com.example.atonce_admin.presentation.home.view.components.DrawerContent
import com.example.atonce_admin.presentation.home.view.components.WareHousesSection
import com.example.atonce_admin.presentation.home.view.components.PieChartCard
import com.example.atonce_admin.presentation.home.view.components.PieChartCardShimmer
import com.example.atonce_admin.presentation.home.view.components.WareHousesSectionShimmer
import com.example.atonce_admin.presentation.home.viewModel.HomeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onDrawerClicked: () -> Unit,
    onProfileClicked: () -> Unit,
    onOrdersClicked: (List<OrderEntity> , String) -> Unit,
    onCustomerClicked: () -> Unit,
    onSeeMoreClick: () -> Unit
) {
    val status = OrderStatesEnum.ORDERED
    val background = MaterialTheme.colorScheme.background

    LaunchedEffect(Unit) {
        viewModel.getControlPanelData(
            pageNumber = 1,
            pageSize = 10,
            status = status.id
        )
    }

    val state = viewModel.controlPanelDataState.collectAsStateWithLifecycle()

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

        when (state.value){
            is Response.Loading ->{
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    CustomSectionShimmer()
                    WareHousesSectionShimmer()
                    CustomSectionShimmer()
                    PieChartCardShimmer()
                }
            }
            is Response.Error -> {
                ErrorView(message = (state.value as Response.Error).message){
                    viewModel.getControlPanelData(
                        pageNumber = 1,
                        pageSize = 10,
                        status = status.id)
                }
            }
            is Response.Success ->{
                val data = (state.value as Response.Success).data

                val totalRevenue = "${data.stats.revenue} EGP".convertNumbersToArabic().replaceEGPWithArabicCurrency()
                val totalCustomers = "${data.stats.pharmacyCount}"
                val pieChartData = listOf(
                    OrderStatesEnum.ORDERED.getLocalizedValue() to data.stats.stats.ordered,
                    OrderStatesEnum.DELIVERED.getLocalizedValue() to data.stats.stats.delivered,
                    OrderStatesEnum.CANCELED.getLocalizedValue() to data.stats.stats.cancelled,
                    OrderStatesEnum.RETURNED.getLocalizedValue() to data.stats.stats.returned
                )

                val warehouses = data.orderState.items.take(3)


                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {

                    CustomSection(
                        header = stringResource(R.string.customers_count),
                        value = totalCustomers.convertNumbersToArabic(),
                        textAlign = TextAlign.Center,
                        bgColor = background,
                    ){
                        onCustomerClicked()
                    }


                    WareHousesSection(warehouses = warehouses,
                        onSeeMoreClick = onSeeMoreClick ,
                        onItemClick = {
                            orders , warehouseName ->
                            onOrdersClicked(orders , warehouseName)
                        } )

                    CustomSection(
                        header = stringResource(R.string.revenue),
                        value = totalRevenue.convertNumbersToArabic(),
                        textAlign = TextAlign.Center,
                        bgColor = background
                    )


                    PieChartCard(
                        title = stringResource(R.string.order_status_overview),
                        data = pieChartData
                    )

                    Spacer(Modifier.height(160.dp))

                }
            }
        }


    }
}

@Composable
fun HomeWithDrawerScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onProfileClicked: () -> Unit,
    onCustomerClicked: () -> Unit,
    onOrdersClicked: (List<OrderEntity> , String) -> Unit,
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
                    userData = viewModel.userData,
                    onItemClick = {
                        title -> onItemClicked(title)
                        scope.launch { drawerState.close() }

                    },
                    onLogout = {
                        scope.launch { drawerState.close() }
                        viewModel.logout()
                        onLogout()
                    },
                    onProfileClicked = {
                        scope.launch { drawerState.close() }
                        onProfileClicked()
                    },
                    onCustomerClicked = {
                        scope.launch { drawerState.close() }
                        onCustomerClicked()
                    }
                )
            }
        }
    ) {
        HomeScreen(
            viewModel = viewModel,
            onDrawerClicked = { scope.launch { drawerState.open() } },
            onProfileClicked = onProfileClicked,
            onOrdersClicked = {
                orders , warehouseName ->
                onOrdersClicked(orders , warehouseName)
            },
            onSeeMoreClick = onSeeMoreClick,
            onCustomerClicked = onCustomerClicked
        )
    }
}
