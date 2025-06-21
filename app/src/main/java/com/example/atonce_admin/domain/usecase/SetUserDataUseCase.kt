package com.example.atonce_admin.domain.usecase


import com.example.atonce_admin.domain.entity.UserModel
import com.example.atonce_admin.domain.repository.Repository


class SetUserDataUseCase(
    private val repository: Repository
){
    operator fun invoke(obj: UserModel) {
        repository.saveUserData(obj)
    }
}