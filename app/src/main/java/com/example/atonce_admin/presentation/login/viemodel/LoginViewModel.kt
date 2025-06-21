package com.example.atonce_admin.presentation.login.viemodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce_admin.data.remote.dto.LoginRequest
import com.example.atonce_admin.domain.usecase.GetLoginResponseUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LoginViewModel(private val getLoginResponseUseCase: GetLoginResponseUseCase) : ViewModel() {
    private val _uiState = MutableSharedFlow<String>()
    val uiState:MutableSharedFlow<String> = _uiState
    fun login(email: String, password: String) {
        viewModelScope.launch {
            if (email.isEmpty() || password.isEmpty()) {
                _uiState.emit("Please fill all fields")
                return@launch
            }
            getLoginResponseUseCase(LoginRequest(email = email, password = password)).catch {
                _uiState.emit( it.message?:"error")
            }.collect { result->
                if (!result.message.isNullOrEmpty()) {
                    _uiState.emit(result.message)
                } else {
                    if(result.errors?.Password.isNullOrEmpty()){
                        _uiState.emit( result.errors?.Email?.first()!!)
                    }else{
                        _uiState.emit(result.errors.Password.first())
                    }
                }

            }
        }
    }

}