package com.example.atonce_admin.di

import com.example.atonce_admin.core.constants.AppConstants
import com.example.atonce_admin.data.remote.service.AuthService
import com.example.atonce_admin.data.remote.service.RepresentativeService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    single{
        Retrofit
            .Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(AuthService::class.java)
    }
    single {
        get<Retrofit>().create(RepresentativeService::class.java)
    }


}