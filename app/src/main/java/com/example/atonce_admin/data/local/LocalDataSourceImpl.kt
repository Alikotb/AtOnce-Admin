package com.example.atonce_admin.data.local

import com.example.atonce_admin.data.local.shared_preference.AppSharedPreference
import com.example.atonce_admin.domain.entity.UserModel

class LocalDataSourceImpl (private val myShared: AppSharedPreference): LocalDataSource {
    override fun saveUserData(obj: UserModel){
        myShared.saveData("address",obj.address)
        myShared.saveData("code",obj.code)
        myShared.saveData("email",obj.email)
        myShared.saveData("governorate",obj.governorate)
        myShared.saveData("id",obj.id)
        myShared.saveData("name",obj.name)
        myShared.saveData("phone",obj.phone)
        myShared.saveData("token",obj.token)


    }
    override fun getUserData(): UserModel{
        return UserModel(
            address = myShared.fetchData("address",""),
            name = myShared.fetchData("name",""),
            governorate = myShared.fetchData("governorate",""),
            id = myShared.fetchData("id",0),
            token =myShared.fetchData("token",""),
            phone = myShared.fetchData("phone",""),
            code =  myShared.fetchData("code",""),
            email =  myShared.fetchData("email","")
        )
    }
}
