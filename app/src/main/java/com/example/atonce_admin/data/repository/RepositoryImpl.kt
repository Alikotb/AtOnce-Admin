package com.example.atonce_admin.data.repository

import com.example.atonce_admin.data.local.LocalDataSource
import com.example.atonce_admin.data.remote.RemoteDataSource
import com.example.atonce_admin.data.remote.dto.LoginRequest
import com.example.atonce_admin.data.remote.dto.LoginResponse
import com.example.atonce_admin.domain.entity.OrderStateEntity
import com.example.atonce_admin.domain.entity.UserModel
import com.example.atonce_admin.domain.mapper.toEntity
import com.example.atonce_admin.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
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
    override  suspend fun login(loginRequest: LoginRequest): Flow<LoginResponse>{
        return remoteDataSource.login(loginRequest = loginRequest)
    }

    override fun saveUserData(obj: UserModel) {
        localDataSource.saveUserData(obj)
    }

    override fun getUserData(): UserModel {
        return localDataSource.getUserData()
    }

    override fun freeUserData() {
        localDataSource.freeUserData()
    }

}