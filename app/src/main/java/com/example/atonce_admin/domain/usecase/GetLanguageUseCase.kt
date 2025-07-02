package com.example.atonce_admin.domain.usecase

import com.example.atonce_admin.domain.repository.Repository

class GetLanguageUseCase(private val repo: Repository) {
    operator fun invoke(): String {
        return repo.getLanguage()
    }
}
