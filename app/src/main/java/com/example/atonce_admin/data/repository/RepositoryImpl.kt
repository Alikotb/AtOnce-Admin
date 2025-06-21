package com.example.atonce_admin.data.repository

import com.example.atonce_admin.data.remote.RemoteDataSource
import com.example.atonce_admin.domain.entity.OrderStateEntity
import com.example.atonce_admin.domain.mapper.toEntity
import com.example.atonce_admin.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : Repository{

    override suspend fun getOrdersByStatus(
        representativeId: Int,
        pageNumber: Int,
        pageSize: Int,
        status: Int
    ): Flow<OrderStateEntity> {
        return remoteDataSource.getOrdersByStatus(
            representativeId = representativeId,
            pageNumber = pageNumber,
            pageSize = pageSize,
            status = status
        ).map {
            it.toEntity()
        }
    }
}