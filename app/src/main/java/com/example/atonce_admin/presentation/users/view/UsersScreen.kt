package com.example.atonce_admin.presentation.users.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.atonce_admin.R
import com.example.atonce_admin.data.Response
import com.example.atonce_admin.presentation.common.component.CustomSearchBar
import com.example.atonce_admin.presentation.common.component.CustomTopBar
import com.example.atonce_admin.presentation.common.component.EmptySearchResultView
import com.example.atonce_admin.presentation.common.component.ErrorView
import com.example.atonce_admin.presentation.users.model.CustomerModel
import com.example.atonce_admin.presentation.users.view.component.UserCard
import com.example.atonce_admin.presentation.users.view.component.UserCardShimmer
import com.example.atonce_admin.presentation.users.viewmodel.UserViewModel
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.compose.koinViewModel


@FlowPreview
@Composable
fun UsersScreen(
    vieModel: UserViewModel = koinViewModel(),
    onItemClicked: (CustomerModel) -> Unit = {},
    onBackClicked: () -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }
    val pharmacyList by vieModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        vieModel.getInitialValue()
        vieModel.getAllCustomer()
    }


    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        CustomTopBar(
            title = stringResource(R.string.customers),
            leadingIcon = Icons.AutoMirrored.Default.ArrowBack,
            onLeadingClick = onBackClicked,
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomSearchBar(
            query = searchText,
            onQueryChange = {
                searchText = it
                vieModel.searchForPharmacy(it)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (pharmacyList) {
            is Response.Loading -> {
                LazyColumn {
                    items(5) {
                        UserCardShimmer()
                    }
                }
            }

            is Response.Error -> {
                val status = (pharmacyList as Response.Error).message
                ErrorView(message = status) {
                    vieModel.getAllCustomer()
                }
            }

            is Response.Success -> {
                val isSearching by vieModel.isSearching.collectAsStateWithLifecycle()
                val list = (pharmacyList as Response.Success<List<CustomerModel>>).data

                if (list.isEmpty() && searchText.isNotEmpty() && !isSearching) {
                    EmptySearchResultView()
                } else {
                    LazyColumn {
                        items(items = list, key = { it.id }) { customer ->
                            UserCard(obj = customer) {
                                onItemClicked(it)
                            }
                        }
                    }
                }
            }
        }
    }

}



