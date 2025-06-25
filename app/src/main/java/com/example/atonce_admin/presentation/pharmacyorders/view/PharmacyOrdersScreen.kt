package com.example.atonce_admin.presentation.pharmacyorders.view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.atonce_admin.data.Response
import com.example.atonce_admin.presentation.common.component.CustomTopBar
import com.example.atonce_admin.presentation.common.component.EmptySearchResultView
import com.example.atonce_admin.presentation.common.component.ErrorView
import com.example.atonce_admin.presentation.common.component.OrderCard
import com.example.atonce_admin.presentation.pharmacyorders.viewmodel.PharmacyOrdersViewModel
import com.example.atonce_admin.presentation.users.model.CustomerModel
import com.example.atonce_admin.presentation.users.view.component.UserCardShimmer
import org.koin.androidx.compose.koinViewModel

@Composable
fun PharmacyOrdersScreen(
    viewModel: PharmacyOrdersViewModel = koinViewModel(),
    pharmacy: CustomerModel,
    onBackClicked: () -> Unit = {}
){

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

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
                            OrderCard(order = it)
                        }
                    }
                }
            }
        }
    }
}