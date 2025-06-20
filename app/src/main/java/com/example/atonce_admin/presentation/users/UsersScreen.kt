package com.example.atonce_admin.presentation.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.atonce_admin.R
import com.example.atonce_admin.presentation.common.component.CustomSearchBar
import com.example.atonce_admin.presentation.common.component.CustomTopBar
import com.example.atonce_admin.presentation.users.component.UserCard


@Composable
fun UsersScreen(
    title : String = "Customers",
    onBackClicked: () -> Unit = {}
){
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize()
    ){
        CustomTopBar(
            title = stringResource(R.string.customers),
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

            items(5){
                UserCard()
            }
        }
    }

}

