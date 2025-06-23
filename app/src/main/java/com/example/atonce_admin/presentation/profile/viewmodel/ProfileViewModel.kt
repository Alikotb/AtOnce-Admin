package com.example.atonce_admin.presentation.profile.viewmodel

import androidx.lifecycle.ViewModel
import com.example.atonce_admin.core.enums.LanguageEnum
import com.example.atonce_admin.domain.usecase.GetUserDataUseCase
import com.example.atonce_admin.domain.usecase.SetLanguageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val setLanguageUseCase: SetLanguageUseCase,
) : ViewModel() {

    val userData
        get()  = getUserDataUseCase()

    private val _LanguageState = MutableStateFlow(LanguageEnum.SYSTEM.getDisplayName(LanguageEnum.SYSTEM))
    val LanguageState = _LanguageState.asStateFlow()

    fun setLanguage(language: LanguageEnum) {
        setLanguageUseCase(language.apiCode)
        _LanguageState.value = language.getDisplayName(language)
    }
}