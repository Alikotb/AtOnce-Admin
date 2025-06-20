package com.example.atonce_admin.di

import com.example.atonce_admin.data.remote.RemoteDataSource
import com.example.atonce_admin.data.remote.RemoteDataSourceImpl
import org.koin.dsl.module


val remoteDataSourceModule = module {
    single<RemoteDataSource>{
        RemoteDataSourceImpl(get())
    }
}