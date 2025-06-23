package com.example.atonce_admin.data.local

import com.example.atonce_admin.domain.entity.UserEntity

interface LocalDataSource {
    fun saveUserData(obj: UserEntity)
    fun getUserData(): UserEntity
    fun freeUserData()
    fun setLanguage(language: String)
    fun getLanguage(): String
}