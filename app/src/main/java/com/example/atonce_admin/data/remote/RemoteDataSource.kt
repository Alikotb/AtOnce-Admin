package com.example.atonce_admin.data.remote

import com.example.atonce_admin.data.remote.dto.OrderStatusResponse
import com.example.atonce_admin.data.remote.dto.StatsResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getRepresentativeStats(representativeId: Int): Flow<StatsResponse>
    suspend fun getOrdersByStatus(representativeId: Int, pageNumber: Int, pageSize: Int, status: Int): Flow<OrderStatusResponse>
}