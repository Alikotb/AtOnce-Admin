package com.example.atonce_admin.presentation.home.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce_admin.core.enums.ErrorEnum
import com.example.atonce_admin.data.Response
import com.example.atonce_admin.domain.entity.ControlPanelEntity
import com.example.atonce_admin.domain.entity.UserEntity
import com.example.atonce_admin.domain.usecase.FreeUserDataUseCase
import com.example.atonce_admin.domain.usecase.GetControlPanelDataUseCase
import com.example.atonce_admin.domain.usecase.GetUserDataUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getControlPanelDataUseCase: GetControlPanelDataUseCase
    ,private val getUserDataUseCase: GetUserDataUseCase,
    private val logoutUseCase: FreeUserDataUseCase
) : ViewModel() {

    private var _controlPanelDataState = MutableStateFlow<Response<ControlPanelEntity>>(Response.Loading)
    val controlPanelDataState = _controlPanelDataState.asStateFlow()

    val userData = getUserDataUseCase()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("TAG", "Caught Exception: ${throwable.message}")
        _controlPanelDataState.value = Response.Error(ErrorEnum.NETWORK_ERROR.getLocalizedMessage())
    }


    fun getControlPanelData(
        pageNumber: Int,
        pageSize: Int,
        status: Int
    ) {
        _controlPanelDataState.value = Response.Loading
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            getControlPanelDataUseCase(userData.id, pageNumber, pageSize, status)
                .collect {
                    _controlPanelDataState.value = Response.Success(it)
                }
        }
    }

    fun logout(){
        logoutUseCase()
    }


}