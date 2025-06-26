package com.example.atonce_admin.di

import com.example.atonce_admin.domain.usecase.GetControlPanelDataUseCase
import com.example.atonce_admin.domain.usecase.FreeUserDataUseCase
import com.example.atonce_admin.domain.usecase.GetAllCustomerUseCase
import com.example.atonce_admin.domain.usecase.GetLanguageUseCase
import com.example.atonce_admin.domain.usecase.GetLoginResponseUseCase
import com.example.atonce_admin.domain.usecase.GetOrdersByStatusUseCase
import com.example.atonce_admin.domain.usecase.GetPharmacyOrderDetails
import com.example.atonce_admin.domain.usecase.GetPharmacyOrdersUseCase
import com.example.atonce_admin.domain.usecase.GetUserDataUseCase
import com.example.atonce_admin.domain.usecase.IsLoggedInUseCase
import com.example.atonce_admin.domain.usecase.SetLanguageUseCase
import com.example.atonce_admin.domain.usecase.SetUserDataUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetOrdersByStatusUseCase(get())
    }
    factory {
        GetControlPanelDataUseCase(get())
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
    factory {
        GetAllCustomerUseCase(get())
    }
    factory { GetLanguageUseCase(get()) }
    factory { SetLanguageUseCase(get()) }
    factory { GetPharmacyOrdersUseCase(get()) }
    factory { GetPharmacyOrderDetails(get()) }
}