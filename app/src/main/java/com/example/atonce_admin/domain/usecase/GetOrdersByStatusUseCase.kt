package com.example.atonce_admin.domain.usecase

import com.example.atonce_admin.domain.repository.Repository

class GetOrdersByStatusUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(
        representativeId: Int,
        pageNumber: Int,
        pageSize: Int,
        status: Int
    ) = repository.getOrdersByStatus(
        representativeId = representativeId,
        pageNumber = pageNumber,
        pageSize = pageSize,
        status = status
    )
}