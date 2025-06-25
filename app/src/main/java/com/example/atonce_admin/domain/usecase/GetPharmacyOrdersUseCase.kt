package com.example.atonce_admin.domain.usecase

import com.example.atonce_admin.domain.entity.OrderEntity
import com.example.atonce_admin.domain.entity.PharmacyOrderResponseEntity
import com.example.atonce_admin.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetPharmacyOrdersUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(pharmacyId: Int): Flow<PharmacyOrderResponseEntity> {
        return repository.getPharmacyOrders(pharmacyId)
    }
}