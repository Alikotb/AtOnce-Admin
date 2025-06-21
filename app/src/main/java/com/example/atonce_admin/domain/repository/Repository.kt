package com.example.atonce_admin.domain.repository

import com.example.atonce_admin.domain.entity.ControlPanelEntity
import com.example.atonce_admin.domain.entity.OrderStateEntity
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
}