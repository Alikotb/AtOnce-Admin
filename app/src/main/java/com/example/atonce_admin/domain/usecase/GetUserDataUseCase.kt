package com.example.atonce_admin.domain.usecase


import com.example.atonce_admin.domain.repository.Repository


class GetUserDataUseCase(
    private val repository: Repository
){
    operator fun invoke()=repository.getUserData()
}