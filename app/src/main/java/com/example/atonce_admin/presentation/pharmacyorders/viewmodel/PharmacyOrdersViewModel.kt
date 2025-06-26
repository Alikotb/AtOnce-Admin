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
) : ViewModel(){

    private val _uiState = MutableStateFlow<Response<List<OrderEntity>>>(Response.Loading)
    val uiState = _uiState.asStateFlow()

    private val _orderDetailsState = MutableStateFlow<Response<List<OrderItem>>>(Response.Loading)
    val orderDetailsState = _orderDetailsState.asStateFlow()

    private val errorExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.value = Response.Error(throwable.message ?: "Something went wrong")
    }

    fun getPharmacyOrders(pharmacy : CustomerModel){
        viewModelScope.launch(Dispatchers.IO + errorExceptionHandler){
            getPharmacyOrdersUseCase(pharmacy.id).collect{
                if (it.result.isNotEmpty()){
                    it.result.forEach {
                        it.pharmacyName = pharmacy.pharmacyName
                        it.userName = pharmacy.userName
                        it.address = pharmacy.address
                    }
                }
                _uiState.value = Response.Success(it.result)
            }
        }
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