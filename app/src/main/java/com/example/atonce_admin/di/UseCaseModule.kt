package com.example.atonce_admin.di

import com.example.atonce_admin.domain.usecase.GetOrdersByStatusUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetOrdersByStatusUseCase(get())
    }
}