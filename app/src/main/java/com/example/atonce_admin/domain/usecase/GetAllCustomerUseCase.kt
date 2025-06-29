package com.example.atonce_admin.domain.usecase

import com.example.atonce_admin.domain.repository.Repository

class GetAllCustomerUseCase(private val repo: Repository) {
    suspend operator fun invoke(id: Int)= repo.getAllCustomer(id)
}