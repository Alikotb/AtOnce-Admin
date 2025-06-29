package com.example.atonce_admin.domain.usecase

import com.example.atonce_admin.data.remote.dto.LoginRequest
import com.example.atonce_admin.data.remote.dto.LoginResponse
import com.example.atonce_admin.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetLoginResponseUseCase(
    private val repository: Repository
){
    suspend operator fun invoke(loginRequest: LoginRequest): Flow<LoginResponse>{
        return repository.login(loginRequest)
    }
}