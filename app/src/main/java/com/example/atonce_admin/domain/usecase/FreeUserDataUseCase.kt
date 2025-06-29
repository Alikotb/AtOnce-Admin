package com.example.atonce_admin.domain.usecase

import com.example.atonce_admin.domain.repository.Repository

class FreeUserDataUseCase(private val repo: Repository) {
    operator fun invoke(){
        repo.freeUserData()
    }
}