package com.example.atonce_admin.presentation.splash.veiwModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.atonce_admin.data.Response
import com.example.atonce_admin.domain.usecase.IsLoggedInUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashViewModel(
    private val isLoggedInUseCase: IsLoggedInUseCase
) : ViewModel() {

    private var _isLoggedIn = MutableStateFlow<Response<Boolean>>(Response.Loading)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    fun isLoggedIn(interval : Long) {
        viewModelScope.launch(Dispatchers.IO){
            delay(interval)
            withContext(Dispatchers.Main){
                _isLoggedIn.value = Response.Success(isLoggedInUseCase())
            }
        }
    }
}