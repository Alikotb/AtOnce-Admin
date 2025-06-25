package com.example.atonce_admin.data.repository

import com.example.atonce_admin.data.local.LocalDataSource
import com.example.atonce_admin.data.remote.RemoteDataSource
import com.example.atonce_admin.data.remote.dto.CustomerResponse
import com.example.atonce_admin.domain.entity.ControlPanelEntity
import com.example.atonce_admin.data.remote.dto.LoginRequest
import com.example.atonce_admin.data.remote.dto.LoginResponse
import com.example.atonce_admin.domain.entity.OrderEntity
import com.example.atonce_admin.domain.entity.OrderStateEntity
import com.example.atonce_admin.domain.entity.UserEntity
import com.example.atonce_admin.data.mapper.toEntity
import com.example.atonce_admin.domain.entity.OrderDetails
import com.example.atonce_admin.domain.entity.PharmacyOrderResponseEntity
import com.example.atonce_admin.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
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

    override fun saveUserData(obj: UserEntity) {
        localDataSource.saveUserData(obj)
    }

    override fun getUserData(): UserEntity {
        return localDataSource.getUserData()
    }

    override fun freeUserData() {
        localDataSource.freeUserData()
    }

    override fun setLanguage(language: String) {
        localDataSource.setLanguage(language)
    }

    override fun getLanguage(): String {
        return localDataSource.getLanguage()
    }

    override suspend fun getAllCustomer(representativeId: Int): Flow<CustomerResponse> {
        return remoteDataSource.getAllCustomer(representativeId)
    }

    override suspend fun getPharmacyOrders(pharmacyId: Int): Flow<PharmacyOrderResponseEntity> {
        return remoteDataSource.getPharmacyOrders(pharmacyId).map {
            it.toEntity()
        }
    }

    override suspend fun getOrderDetails(orderId: Int): Flow<OrderDetails> {
        return remoteDataSource.getOrderDetails(orderId).map { it.toEntity() }
    }

    override suspend fun getControlPanelData(
        representativeId: Int,
        pageNumber: Int,
        pageSize: Int,
        status: Int
    ): Flow<ControlPanelEntity> {

        val stats = remoteDataSource.getRepresentativeStats(representativeId)
        val orders = remoteDataSource.getOrdersByStatus(representativeId, pageNumber, pageSize, status)

        return combine(orders, stats) { orderResponse, statsResponse ->
            ControlPanelEntity(
                orderState = orderResponse.toEntity(),
                stats = statsResponse.toEntity()
            )
        }

    }
}