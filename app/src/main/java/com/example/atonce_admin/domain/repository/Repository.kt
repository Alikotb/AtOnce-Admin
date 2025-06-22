package com.example.atonce_admin.domain.repository

import com.example.atonce_admin.data.remote.dto.CustomerResponse
import com.example.atonce_admin.domain.entity.ControlPanelEntity
import com.example.atonce_admin.data.remote.dto.LoginRequest
import com.example.atonce_admin.data.remote.dto.LoginResponse
import com.example.atonce_admin.domain.entity.OrderStateEntity
import com.example.atonce_admin.domain.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getOrdersByStatus(
        representativeId: Int,
        pageNumber: Int,
        pageSize: Int,
        status: Int
    ): Flow<OrderStateEntity>

    suspend fun getControlPanelData(
        representativeId: Int,
        pageNumber: Int,
        pageSize: Int,
        status: Int
    ): Flow<ControlPanelEntity>
    suspend fun login(loginRequest: LoginRequest): Flow<LoginResponse>
    fun saveUserData(obj: UserEntity)
    fun getUserData(): UserEntity
    fun freeUserData()
    suspend fun getAllCustomer(representativeId: Int): Flow<CustomerResponse>

}