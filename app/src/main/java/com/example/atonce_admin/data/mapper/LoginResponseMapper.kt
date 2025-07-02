package com.example.atonce_admin.data.mapper

import com.example.atonce_admin.data.remote.dto.LoginResponse
import com.example.atonce_admin.domain.entity.UserEntity

fun LoginResponse.toEntity(): UserEntity{
    return UserEntity(
        email = representative.email ?:"",
        id = representative.id ?:0,
        code = representative.code ?:"",
        name = representative.name ?:"",
        phone = representative.phone ?:"",
        token = token?:"",
        address = representative.address ?:"",
        governorate = representative.governate ?:""
    )
}