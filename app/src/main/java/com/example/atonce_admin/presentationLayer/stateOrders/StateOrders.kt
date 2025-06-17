package com.example.atonce_admin.presentationLayer.stateOrders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce_admin.presentationLayer.component.CustomSearchBar
import com.example.atonce_admin.presentationLayer.component.CustomTopBar
import com.example.atonce_admin.presentationLayer.component.OrderRowItem
import com.example.atonce_admin.presentationLayer.theme.SemiBoldFont


@Preview(showBackground = true)
@Composable
fun StateOrders(
    orders: List<Triple<String, Int, String>> = listOf(
        Triple("Hamada Pharma Company", 3, "199 EGP"),
        Triple("Hamada Pharma Company", 3, "199 EGP"),
        Triple("Hamada Pharma Company", 3, "199 EGP"),
        Triple("Hamada Pharma Company", 3, "199 EGP"),
        Triple("Hamada Pharma Company", 3, "199 EGP"),
        Triple("Hamada Pharma Company", 3, "199 EGP"),
        Triple("Hamada Pharma Company", 3, "199 EGP"),
        Triple("Hamada Pharma Company", 3, "199 EGP")
    ),
    title : String = "New Orders",
    onBackClicked: () -> Unit = {},

){

    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize()
    ){
        CustomTopBar(
            title = title,
            leadingIcon = Icons.AutoMirrored.Default.ArrowBack,
            onLeadingClick = onBackClicked,
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomSearchBar(
            query = searchText,
            onQueryChange = { searchText = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
        ){

            items(items = orders){
                OrderRowItem(
                    companyName = it.first,
                    newOrdersCount = it.second,
                    price = it.third
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = Color.LightGray.copy(alpha = 0.6f)
                    , modifier = Modifier.padding(horizontal = 8.dp))
            }
        }
    }




}