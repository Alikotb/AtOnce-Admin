package com.example.atonce_admin.di

import com.example.atonce_admin.data.repository.RepositoryImpl
import com.example.atonce_admin.domain.repository.Repository
import org.koin.dsl.module

val repositoryModule =
    module {
        single<Repository>{
            RepositoryImpl(get(),get())
        }
    }