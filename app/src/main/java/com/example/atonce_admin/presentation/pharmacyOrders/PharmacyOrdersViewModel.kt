package com.example.atonce_admin.presentation.pharmacyOrders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce_admin.data.Response
import com.example.atonce_admin.domain.entity.OrderEntity
import com.example.atonce_admin.domain.usecase.GetPharmacyOrdersUseCase
import com.example.atonce_admin.presentation.users.model.CustomerModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PharmacyOrdersViewModel(
    private val getPharmacyOrdersUseCase: GetPharmacyOrdersUseCase
) : ViewModel(){


    private val _uiState = MutableStateFlow<Response<List<OrderEntity>>>(Response.Loading)
    val uiState = _uiState.asStateFlow()


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

}