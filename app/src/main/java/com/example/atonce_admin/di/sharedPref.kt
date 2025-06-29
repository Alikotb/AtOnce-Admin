package com.example.atonce_admin.di

import com.example.atonce_admin.data.local.shared_preference.AppSharedPreference
import com.example.atonce_admin.data.local.shared_preference.AppSharedPreferenceImp
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sharedPreference = module {
    single<AppSharedPreference>{
        AppSharedPreferenceImp(androidContext())
    }
}