package com.example.atonce_admin.di

import StatusOrderViewModel
import com.example.atonce_admin.presentation.home.viewModel.HomeViewModel
import com.example.atonce_admin.presentation.login.viemodel.LoginViewModel
import com.example.atonce_admin.presentation.splash.veiwModel.SplashViewModel
import com.example.atonce_admin.presentation.users.viewmodel.UserViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        StatusOrderViewModel(get())
    }
    viewModel {
        HomeViewModel(get() , get() , get())
    }

    viewModel {
        LoginViewModel(get(),get())
    }
    viewModel {
        UserViewModel(get(),get())
    }

    viewModel {
        SplashViewModel(get())
    }
}