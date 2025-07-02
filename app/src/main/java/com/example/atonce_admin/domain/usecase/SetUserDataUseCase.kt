package com.example.atonce_admin.domain.usecase


import com.example.atonce_admin.domain.entity.UserEntity
import com.example.atonce_admin.domain.repository.Repository


class SetUserDataUseCase(
    private val repository: Repository
){
    operator fun invoke(obj: UserEntity) {
        repository.saveUserData(obj)
    }
}