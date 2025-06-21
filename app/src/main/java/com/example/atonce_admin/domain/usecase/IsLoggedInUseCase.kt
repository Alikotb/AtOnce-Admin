package com.example.atonce_admin.domain.usecase

import com.example.atonce_admin.domain.repository.Repository

class IsLoggedInUseCase(private val repo: Repository) {
    operator fun invoke() =  repo.getUserData().id==-1
}