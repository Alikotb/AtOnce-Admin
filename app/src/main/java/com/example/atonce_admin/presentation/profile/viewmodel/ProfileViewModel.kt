package com.example.atonce_admin.presentation.profile.viewmodel

import androidx.lifecycle.ViewModel
import com.example.atonce_admin.domain.usecase.GetUserDataUseCase

class ProfileViewModel(private val getUserDataUseCase: GetUserDataUseCase) : ViewModel() {
    val userData
        get()  = getUserDataUseCase()
}