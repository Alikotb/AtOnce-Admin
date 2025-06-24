package com.example.atonce_admin.domain.mapper

import com.example.atonce_admin.data.remote.dto.CustomerResponse
import com.example.atonce_admin.presentation.users.model.CustomerModel
import kotlin.String


fun CustomerResponse.Pharmacy.toEntity(): CustomerModel {
    return CustomerModel(
        address = address,
        areaId = areaId,
        areaName = "",
        governorateAndArea = "$governate, $areaName",
        id =id,
        userName = userName ?: "",
        pharmacyName = name,
        phoneNumber = phoneNumber
    )
}

