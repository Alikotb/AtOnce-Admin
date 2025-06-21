package com.example.atonce_admin.data.local

import android.service.autofill.UserData
import com.example.atonce_admin.domain.entity.UserModel

interface LocalDataSource {
    fun saveUserData(obj: UserModel)
    fun getUserData(): UserModel

}