package com.example.atonce_admin.presentation.users.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce_admin.core.enums.ErrorEnum
import com.example.atonce_admin.data.Response
import com.example.atonce_admin.data.remote.dto.CustomerResponse
import com.example.atonce_admin.domain.mapper.toEntity
import com.example.atonce_admin.domain.usecase.GetAllCustomerUseCase
import com.example.atonce_admin.domain.usecase.GetUserDataUseCase
import com.example.atonce_admin.presentation.users.model.CustomerModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@FlowPreview
class UserViewModel(
    private val getAllCustomerUseCase: GetAllCustomerUseCase,
    private val getUserDataUseCase: GetUserDataUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<Response<List<CustomerModel>>>(Response.Loading)
    private var searchList = mutableListOf<CustomerModel>()
    val uiState = _uiState.asStateFlow()
    private val _searchQuery = MutableStateFlow("")

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("TAG", "Caught Exception: ${throwable.message}")
        _uiState.value = Response.Error(ErrorEnum.NETWORK_ERROR.getLocalizedMessage())
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _searchQuery
                .debounce(500)
                .distinctUntilChanged()
                .collectLatest { query ->
                    performSearch(query)
                }
        }
    }


    fun getAllCustomer() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            _uiState.emit(Response.Loading)
            try {
                getAllCustomerUseCase(getUserDataUseCase().id).catch {
                    _uiState.emit(Response.Error(it.message ?: "error"))
                }.collect { result: CustomerResponse ->
                    val list = result.pharmacies.map { it.toEntity() }
                    searchList.clear()

                    searchList.addAll(list)
                    _uiState.emit(Response.Success(data = list))
                }
            } catch (e: Error) {
                _uiState.emit(Response.Error(ErrorEnum.NETWORK_ERROR.getLocalizedMessage()))
            }
        }
    }

    fun searchForPharmacy(query: String) {
        _searchQuery.value = query
    }
    private  fun performSearch(searchTxt: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = if (searchTxt.isNotBlank()) {
                    searchList.filter {
                        it.userName.contains(searchTxt,ignoreCase = true) || it.pharmacyName.contains(searchTxt,ignoreCase = true)
                    }
                } else {
                    searchList
                }
                _uiState.emit(Response.Success(data = result))
            } catch (e: Error) {
                _uiState.emit(Response.Error(e.message ?: ""))
            }
        }
    }
}