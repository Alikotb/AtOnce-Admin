package com.example.atonce_admin.di

import StatusOrderViewModel
import com.example.atonce_admin.presentation.home.viewModel.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        StatusOrderViewModel(get())
    }
    viewModel {
        HomeViewModel(get())
    }

}