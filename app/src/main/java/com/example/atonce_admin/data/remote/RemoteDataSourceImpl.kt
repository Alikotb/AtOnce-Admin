package com.example.atonce_admin.data.remote

import com.example.atonce_admin.data.remote.service.AuthService

class RemoteDataSourceImpl(
    private val authService: AuthService
) : RemoteDataSource {
}