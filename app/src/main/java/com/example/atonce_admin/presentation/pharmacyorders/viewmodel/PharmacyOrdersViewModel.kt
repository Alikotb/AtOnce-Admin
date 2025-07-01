package com.example.atonce_admin.presentation.pharmacyorders.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce_admin.data.Response
import com.example.atonce_admin.domain.entity.OrderEntity
import com.example.atonce_admin.domain.entity.OrderItem
import com.example.atonce_admin.domain.usecase.GetPharmacyOrderDetails
import com.example.atonce_admin.domain.usecase.GetPharmacyOrdersUseCase
import com.example.atonce_admin.presentation.users.model.CustomerModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PharmacyOrdersViewModel(
    private val getPharmacyOrdersUseCase: GetPharmacyOrdersUseCase,
    private val getOrderDetailsUseCase: GetPharmacyOrderDetails
) : ViewModel() {

    private val _uiState = MutableStateFlow<Response<List<OrderEntity>>>(Response.Loading)
    val uiState = _uiState.asStateFlow()

    private val _orderDetailsState = MutableStateFlow<Response<List<OrderItem>>>(Response.Loading)
    val orderDetailsState = _orderDetailsState.asStateFlow()

    private val errorExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.value = Response.Error(throwable.message ?: "Something went wrong")
    }

    private var currentPage = 1
    private val pageSize = 20
    private var isLastPage = false
    private var isLoading = false
    private val orders = mutableListOf<OrderEntity>()
    private var pharmacyModel: CustomerModel? = null

    fun getPharmacyOrders(pharmacy: CustomerModel, reset: Boolean = false) {
        if (isLoading || isLastPage) return

        if (reset) {
            currentPage = 1
            isLastPage = false
            orders.clear()
            _uiState.value = Response.Loading
        }

        isLoading = true
        pharmacyModel = pharmacy

        viewModelScope.launch(Dispatchers.IO + errorExceptionHandler) {
            getPharmacyOrdersUseCase(pharmacy.id, currentPage, pageSize).collect { result ->
                if (result.result.isNotEmpty()) {
                    result.result.forEach {
                        it.pharmacyName = pharmacy.pharmacyName
                        it.userName = pharmacy.userName
                        it.address = pharmacy.address
                    }
                    orders.addAll(result.result)
                    _uiState.value = Response.Success(orders.toList())
                    currentPage++
                    if (result.result.size < pageSize) isLastPage = true
                } else {
                    isLastPage = true
                }
                isLoading = false
            }
        }
    }

    fun loadNextPage() {
        pharmacyModel?.let {
            getPharmacyOrders(it)
        }
    }

    fun refreshOrders(pharmacy: CustomerModel) {
        getPharmacyOrders(pharmacy, reset = true)
    }

    fun getOrderDetails(orderId: Int) {
        viewModelScope.launch(Dispatchers.IO + errorExceptionHandler) {
            _orderDetailsState.value = Response.Loading
            getOrderDetailsUseCase(orderId).collect {
                _orderDetailsState.value = Response.Success(it.result)
            }
        }
    }
}
