package com.example.atonce_admin.data.remote

import com.example.atonce_admin.data.remote.dto.OrderStatusResponse
import com.example.atonce_admin.data.remote.dto.StatsResponse
import com.example.atonce_admin.data.remote.service.AuthService
import com.example.atonce_admin.data.remote.service.RepresentativeService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RemoteDataSourceImpl(
    private val authService: AuthService,
    private val representativeService: RepresentativeService
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


}