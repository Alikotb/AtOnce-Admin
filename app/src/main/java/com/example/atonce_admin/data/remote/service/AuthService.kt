package com.example.atonce_admin.data.remote.service

import com.example.atonce_admin.data.remote.dto.LoginRequest
import com.example.atonce_admin.data.remote.dto.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/Representative/Login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
}