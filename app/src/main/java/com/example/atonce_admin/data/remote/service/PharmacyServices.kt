package com.example.atonce_admin.data.remote.service

import com.example.atonce_admin.data.remote.dto.PharmacyOrdersResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PharmacyServices {

    @GET("api/order/getAllorder/{representativeId}")
    suspend fun getPharmacyOrders(@Path("representativeId") representativeId: Int): PharmacyOrdersResponse

}