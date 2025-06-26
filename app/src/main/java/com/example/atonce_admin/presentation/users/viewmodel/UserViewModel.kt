package com.example.atonce_admin.presentation.users.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce_admin.core.enums.ErrorEnum
import com.example.atonce_admin.data.Response
import com.example.atonce_admin.data.remote.dto.CustomerResponse
import com.example.atonce_admin.data.mapper.toEntity
import com.example.atonce_admin.domain.usecase.GetAllCustomerUseCase
import com.example.atonce_admin.domain.usecase.GetUserDataUseCase
import com.example.atonce_admin.presentation.users.model.CustomerModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@FlowPreview
class UserViewModel(
    private val getAllCustomerUseCase: GetAllCustomerUseCase,
    private val getUserDataUseCase: GetUserDataUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<Response<List<CustomerModel>>>(Response.Loading)
    private val _allCustomers = MutableStateFlow<List<CustomerModel>>(emptyList())
    val uiState = _uiState.asStateFlow()
    private val _searchQuery = MutableStateFlow("")
    private val _isCustomerDataLoaded = MutableStateFlow(false)
    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.value = Response.Error(ErrorEnum.NETWORK_ERROR.getLocalizedMessage())
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            combine(
                _searchQuery.debounce(500),
                _isCustomerDataLoaded
            ) { query, isLoaded ->
                Pair(query, isLoaded)
            }
                .distinctUntilChanged()
                .collectLatest { (query, isLoaded) ->
                    if (isLoaded) {
                        _isSearching.value = true
                        filterCustomers(query)
                        _isSearching.value = false
                    }
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
                    _allCustomers.emit(list)
                    _allCustomers.emit(list)
                    _isCustomerDataLoaded.value = true
                }
            } catch (e: Error) {
                _uiState.emit(Response.Error(ErrorEnum.NETWORK_ERROR.getLocalizedMessage()))
            }

        }

    }

    fun searchForPharmacy(query: String) {
        _searchQuery.value = query
    }

    private fun filterCustomers(query: String) {
        val allCustomers = _allCustomers.value
        val filtered = if (query.isBlank()) {
            allCustomers
        } else {
            allCustomers.filter {
                it.userName.contains(query, ignoreCase = true) ||
                        it.pharmacyName.contains(query, ignoreCase = true)
            }
        }
        _uiState.value = Response.Success(filtered)
    }
}
