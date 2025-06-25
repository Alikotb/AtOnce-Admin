package com.example.atonce_admin.data.remote

import com.example.atonce_admin.data.remote.dto.CustomerResponse
import com.example.atonce_admin.data.remote.dto.LoginRequest
import com.example.atonce_admin.data.remote.dto.LoginResponse
import com.example.atonce_admin.data.remote.dto.OrderStatusResponse
import com.example.atonce_admin.data.remote.dto.PharmacyOrdersResponse
import com.example.atonce_admin.data.remote.dto.StatsResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getRepresentativeStats(representativeId: Int): Flow<StatsResponse>
    suspend fun getOrdersByStatus(representativeId: Int, pageNumber: Int, pageSize: Int, status: Int): Flow<OrderStatusResponse>
    suspend fun login(loginRequest: LoginRequest): Flow<LoginResponse>
    suspend fun getAllCustomer(representativeId: Int): Flow<CustomerResponse>
    suspend fun getPharmacyOrders(pharmacyId: Int): Flow<PharmacyOrdersResponse>
}