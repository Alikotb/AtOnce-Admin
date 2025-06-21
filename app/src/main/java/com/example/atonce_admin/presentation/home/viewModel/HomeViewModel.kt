package com.example.atonce_admin.presentation.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce_admin.data.Response
import com.example.atonce_admin.domain.entity.ControlPanelEntity
import com.example.atonce_admin.domain.usecase.GetControlPanelDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getControlPanelDataUseCase: GetControlPanelDataUseCase
) : ViewModel() {

    private var _controlPanelDataState = MutableStateFlow<Response<ControlPanelEntity>>(Response.Loading)
    val controlPanelDataState = _controlPanelDataState.asStateFlow()


    fun getControlPanelData(
        representativeId: Int,
        pageNumber: Int,
        pageSize: Int,
        status: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            getControlPanelDataUseCase(representativeId, pageNumber, pageSize, status)
                .catch {
                    _controlPanelDataState.value = Response.Error(it.message.toString())
                }
                .collect {
                    _controlPanelDataState.value = Response.Success(it)
                }
        }

    }


}