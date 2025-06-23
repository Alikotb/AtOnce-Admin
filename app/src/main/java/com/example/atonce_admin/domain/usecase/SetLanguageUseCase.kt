package com.example.atonce_admin.domain.usecase

import com.example.atonce_admin.domain.repository.Repository

class SetLanguageUseCase(private val repo: Repository) {
    operator fun invoke(language: String) {
        repo.setLanguage(language)
    }
}