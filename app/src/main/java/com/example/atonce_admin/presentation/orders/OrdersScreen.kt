package com.example.atonce_admin.presentation.orders

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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.atonce_admin.data.Response
import com.example.atonce_admin.presentation.common.component.OrderCard
import com.example.atonce_admin.presentation.common.component.CustomTopBar
import com.example.atonce_admin.domain.entity.OrderEntity
import com.example.atonce_admin.domain.entity.OrderItem
import com.example.atonce_admin.presentation.pharmacyorders.view.component.OrderItemCard
import com.example.atonce_admin.presentation.pharmacyorders.view.component.OrderItemShimmerCard

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OrdersScreen(
    orders : List<OrderEntity>,
    title: String ,
    onBackClicked: () -> Unit = {}
){

    val showBottomSheet = remember { mutableStateOf(false) }

    var list by remember { mutableStateOf<List<OrderItem>>(emptyList()) }

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
                    order = it,
                ){
                    showBottomSheet.value = true
                    list = it
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
            Log.d("TAG", "OrdersScreen list: $list")
            LazyColumn {
                items(list) { item ->
                    OrderItemCard(item)
                }
            }
        }
    }
}
