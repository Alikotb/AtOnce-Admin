package com.example.atonce_admin.presentation.pharmacyorders.view

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.atonce_admin.data.Response
import com.example.atonce_admin.presentation.common.component.CustomTopBar
import com.example.atonce_admin.presentation.common.component.EmptySearchResultView
import com.example.atonce_admin.presentation.common.component.ErrorView
import com.example.atonce_admin.presentation.common.component.OrderCard
import com.example.atonce_admin.presentation.pharmacyorders.view.component.OrderItemCard
import com.example.atonce_admin.presentation.pharmacyorders.view.component.OrderItemShimmerCard
import com.example.atonce_admin.presentation.pharmacyorders.viewmodel.PharmacyOrdersViewModel
import com.example.atonce_admin.presentation.users.model.CustomerModel
import com.example.atonce_admin.presentation.users.view.component.UserCardShimmer
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PharmacyOrdersScreen(
    viewModel: PharmacyOrdersViewModel = koinViewModel(),
    pharmacy: CustomerModel,
    onBackClicked: () -> Unit = {}
){

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    val showBottomSheet = remember { mutableStateOf(false) }
    val selectedOrderId = remember { mutableStateOf<Int?>(null) }
    val orderDetailsState = viewModel.orderDetailsState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit){
        viewModel.getPharmacyOrders(pharmacy)
    }

    Column(
        modifier = Modifier.padding(8.dp)
    ){
        CustomTopBar(
            title = pharmacy.pharmacyName,
            leadingIcon = Icons.AutoMirrored.Default.ArrowBack,
            onLeadingClick = onBackClicked
        )
        when(uiState.value){
            is Response.Loading -> {
                LazyColumn {
                    items(5) {
                        UserCardShimmer()
                    }
                }
            }
            is Response.Error -> {
                val status = (uiState.value as Response.Error).message
                Log.e("TAG", "PharmacyOrdersScreen: ", )
                ErrorView(message = status) {
                    viewModel.getPharmacyOrders(pharmacy)
                }
            }
            is Response.Success -> {
                val orders = (uiState.value as Response.Success).data
                if (orders.isEmpty()) {
                    EmptySearchResultView()
                }
                else {
                    LazyColumn {
                        items(orders){
                            OrderCard(
                                isCustomer = true,
                                order = it,
                                onItemClick = {
                                    showBottomSheet.value = true
                                    selectedOrderId.value = it.orderId
                                    viewModel.getOrderDetails(it.orderId)
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    if (showBottomSheet.value) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet.value = false },
            containerColor = MaterialTheme.colorScheme.surface,
            scrimColor = Color.Black.copy(alpha = 0.2f)
        ) {
            when (orderDetailsState.value) {
                is Response.Loading -> {
                    LazyColumn{
                        items(2){
                            OrderItemShimmerCard()
                        }
                    }
                }
                is Response.Success -> {
                    val items = (orderDetailsState.value as Response.Success).data
                    LazyColumn {
                        items(items) { item ->
                            OrderItemCard(item)
                        }
                    }
                }
                is Response.Error -> {

                }
            }
        }
    }

}