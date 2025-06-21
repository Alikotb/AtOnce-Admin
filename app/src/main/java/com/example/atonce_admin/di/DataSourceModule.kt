package com.example.atonce_admin.di

import com.example.atonce_admin.data.local.LocalDataSource
import com.example.atonce_admin.data.local.LocalDataSourceImpl
import com.example.atonce_admin.data.remote.RemoteDataSource
import com.example.atonce_admin.data.remote.RemoteDataSourceImpl
import org.koin.dsl.module


val remoteDataSourceModule = module {
    single<RemoteDataSource>{
        RemoteDataSourceImpl(get(), get())
    }
}
val localDataSourceModule = module {
    single<LocalDataSource>{
        LocalDataSourceImpl(get())
    }
}