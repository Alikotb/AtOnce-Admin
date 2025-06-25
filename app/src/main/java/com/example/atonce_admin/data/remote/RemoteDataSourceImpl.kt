package com.example.atonce_admin.data.remote

import com.example.atonce_admin.data.remote.dto.CustomerResponse
import com.example.atonce_admin.data.remote.dto.LoginRequest
import com.example.atonce_admin.data.remote.dto.LoginResponse
import com.example.atonce_admin.data.remote.dto.OrderDetailsResponseDto
import com.example.atonce_admin.data.remote.dto.OrderStatusResponse
import com.example.atonce_admin.data.remote.dto.PharmacyOrdersResponse
import com.example.atonce_admin.data.remote.dto.StatsResponse
import com.example.atonce_admin.data.remote.service.AuthService
import com.example.atonce_admin.data.remote.service.PharmacyServices
import com.example.atonce_admin.data.remote.service.RepresentativeService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RemoteDataSourceImpl(
    private val authService: AuthService,
    private val representativeService: RepresentativeService,
    private val pharmacyService: PharmacyServices
) : RemoteDataSource {


    override suspend fun getRepresentativeStats(representativeId: Int): Flow<StatsResponse> {
        return flowOf(
            representativeService.getOrdersStats(representativeId)
        )
    }

    override suspend fun getOrdersByStatus(
        representativeId: Int,
        pageNumber: Int,
        pageSize: Int,
        status: Int
    ): Flow<OrderStatusResponse> {
        return flowOf(
            representativeService.getOrdersByStatus(representativeId, pageNumber, pageSize, status)
        )
    }
    override suspend fun login(loginRequest: LoginRequest): Flow<LoginResponse>{
        return flowOf(
            authService.login(loginRequest = loginRequest)
        )
    }

    override suspend fun getAllCustomer(representativeId: Int): Flow<CustomerResponse> {
        return flowOf(representativeService.getAllCustomers(representativeId))
    }

    override suspend fun getPharmacyOrders(pharmacyId: Int): Flow<PharmacyOrdersResponse> {
        return flowOf(pharmacyService.getPharmacyOrders(pharmacyId))
    }

    override suspend fun getOrderDetails(orderId: Int): Flow<OrderDetailsResponseDto> {
        return flowOf(pharmacyService.getOrderDetails(orderId))
    }


}