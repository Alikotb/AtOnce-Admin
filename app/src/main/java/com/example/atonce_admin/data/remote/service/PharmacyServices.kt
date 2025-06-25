package com.example.atonce_admin.data.remote.service

import com.example.atonce_admin.data.remote.dto.OrderDetailsResponseDto
import com.example.atonce_admin.data.remote.dto.PharmacyOrdersResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PharmacyServices {

    @GET("api/order/getAllorder/{pharmacyId}")
    suspend fun getPharmacyOrders(@Path("pharmacyId") pharmacyId: Int): PharmacyOrdersResponse

    @GET("api/Order/getAllOrderDetails/{orderId}")
    suspend fun getOrderDetails(@Path("orderId") orderId: Int): OrderDetailsResponseDto

}