package com.example.atonce_admin.domain.usecase

import com.example.atonce_admin.domain.entity.OrderDetails
import com.example.atonce_admin.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetPharmacyOrderDetails(private val repository: Repository) {
    suspend operator fun invoke(orderId: Int): Flow<OrderDetails> {
        return repository.getOrderDetails(orderId)
    }
}