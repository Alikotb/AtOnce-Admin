package com.example.atonce_admin

import android.app.Application
import com.example.atonce_admin.di.networkModule
import com.example.atonce_admin.di.remoteDataSourceModule
import com.example.atonce_admin.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    remoteDataSourceModule,
                    repositoryModule
                )
            )
        }
    }
}