package com.example.atonce_admin.presentation.login.viemodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce_admin.core.enums.ErrorEnum
import com.example.atonce_admin.data.Response
import com.example.atonce_admin.data.remote.dto.LoginRequest
import com.example.atonce_admin.data.remote.dto.LoginResponse
import com.example.atonce_admin.domain.mapper.toEntity
import com.example.atonce_admin.domain.usecase.GetLoginResponseUseCase
import com.example.atonce_admin.domain.usecase.SetUserDataUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LoginViewModel(
    private val getLoginResponseUseCase: GetLoginResponseUseCase,
    private val setUserDataUseCase: SetUserDataUseCase
) : ViewModel() {
    private val _message = MutableSharedFlow<String>()
    val message: MutableSharedFlow<String> = _message

    private val _loginSuccess = MutableSharedFlow<Boolean>()
    val loginSuccess = _loginSuccess.asSharedFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("TAG", "Caught Exception: ${throwable.message}")
        viewModelScope.launch {
            _isLoading.value = false
            _message.emit(ErrorEnum.NETWORK_ERROR.getLocalizedMessage())
        }
    }

    fun login(email: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO + exceptionHandler){
            if (email.isEmpty() || password.isEmpty()) {
                _isLoading.value = false
                _message.emit("Please fill all fields")
                return@launch
            }
            getLoginResponseUseCase(LoginRequest(email = email, password = password)).catch {
                _isLoading.value = false
                _message.emit(it.message ?: "error")
            }.collect { result: LoginResponse ->
                _isLoading.value = false
                if (result.success == true) {
                    setUserDataUseCase(result.toEntity())
                    _loginSuccess.emit(true)
                } else {
                    _message.emit(result.message)
                }

            }
        }
    }

}