package com.example.atonce_admin.di

import StatusOrderViewModel
import com.example.atonce_admin.presentation.home.viewModel.HomeViewModel
import com.example.atonce_admin.presentation.login.viemodel.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        StatusOrderViewModel(get())
    }
    viewModel {
        HomeViewModel(get())
    }

    viewModel {
        LoginViewModel(get(),get())
    }
}