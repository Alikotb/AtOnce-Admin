package com.example.atonce_admin.di

import com.example.atonce_admin.presentation.stateOrders.viewModel.StatusOrderViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        StatusOrderViewModel(get())
    }
}