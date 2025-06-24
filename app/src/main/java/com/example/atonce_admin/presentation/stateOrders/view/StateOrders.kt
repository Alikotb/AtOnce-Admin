package com.example.atonce_admin.presentation.stateOrders.view

import StatusOrderViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.atonce_admin.core.enums.OrderStatesEnum
import com.example.atonce_admin.data.Response
import com.example.atonce_admin.domain.entity.OrderEntity
import com.example.atonce_admin.presentation.common.component.CustomSearchBar
import com.example.atonce_admin.presentation.common.component.CustomTopBar
import com.example.atonce_admin.presentation.common.component.EmptySearchResultView
import com.example.atonce_admin.presentation.common.component.ErrorView
import com.example.atonce_admin.presentation.common.component.WarehouseRowItem
import com.example.atonce_admin.presentation.common.component.WarehouseRowShimmerItem
import org.koin.androidx.compose.koinViewModel


@Composable
fun StateOrders(
    viewModel: StatusOrderViewModel = koinViewModel(),
    type : OrderStatesEnum = OrderStatesEnum.ORDERED,
    onBackClicked: () -> Unit = {},
    onItemClick: (List<OrderEntity> , String) -> Unit
){
    val id = 1

    var searchText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.loadNextPage(id, type.id)
    }

    val state by viewModel.ordersState.collectAsStateWithLifecycle()

    val listState = rememberLazyListState()
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            val totalItems = listState.layoutInfo.totalItemsCount
            lastVisibleItem != null && lastVisibleItem >= totalItems - 1
        }
    }

    LaunchedEffect(shouldLoadMore.value) {
        if (shouldLoadMore.value) {
            viewModel.loadNextPage(id, type.id)
        }
    }


    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ){
        CustomTopBar(
            title = type.getLocalizedTitle(),
            leadingIcon = Icons.AutoMirrored.Default.ArrowBack,
            onLeadingClick = onBackClicked,
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomSearchBar(
            query = searchText,
            onQueryChange = { searchText = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        when(state){
            is Response.Error -> {
                ErrorView(message = (state as Response.Error).message){
                    viewModel.loadNextPage(id, type.id , default = true)
                }
            }
            is Response.Loading -> {
                LazyColumn {
                    items(3) {
                        WarehouseRowShimmerItem()
                    }
                }
            }
            is Response.Success -> {
                val allWarehouses = (state as Response.Success).data
                val filteredWarehouses = if (searchText.isBlank()) {
                    allWarehouses
                } else {
                    allWarehouses.filter {
                        it.warehouseName.contains(searchText, ignoreCase = true)
                    }
                }

                if (filteredWarehouses.isEmpty()) {
                    EmptySearchResultView()
                } else {
                    LazyColumn(state = listState) {
                        items(filteredWarehouses) {
                            WarehouseRowItem(
                                warehouse = it,
                                onItemClick = { orders, title ->
                                    onItemClick(orders, title)
                                }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Divider(
                                color = Color.LightGray.copy(alpha = 0.6f),
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                        }
                    }
                }
            }

        }
    }
}

