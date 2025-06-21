package com.example.atonce_admin.di

import com.example.atonce_admin.domain.usecase.FreeUserDataUseCase
import com.example.atonce_admin.domain.usecase.GetLoginResponseUseCase
import com.example.atonce_admin.domain.usecase.GetOrdersByStatusUseCase
import com.example.atonce_admin.domain.usecase.GetUserDataUseCase
import com.example.atonce_admin.domain.usecase.IsLoggedInUseCase
import com.example.atonce_admin.domain.usecase.SetUserDataUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetOrdersByStatusUseCase(get())
    }
    factory {
        GetLoginResponseUseCase(get())
    }
    factory {
        GetUserDataUseCase(get())
    }
    factory {
        SetUserDataUseCase(get())
    }
    factory {
        FreeUserDataUseCase(get())
    }
    factory {
        IsLoggedInUseCase(get())
    }

}