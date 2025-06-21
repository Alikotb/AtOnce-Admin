package com.example.atonce_admin.data.remote.service

import com.example.atonce_admin.data.remote.dto.OrderStatusResponse
import com.example.atonce_admin.data.remote.dto.StatsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepresentativeService {
    @GET("/api/Representative/orders-stats/{representativeId}")
    suspend fun getOrdersStats(@Path("representativeId") representativeId: Int): StatsResponse

    @GET("api/Representative/{representativeId}/orders-by-status")
    suspend fun getOrdersByStatus(
        @Path("representativeId") representativeId: Int,
        @Query("pageNumber") pageNumber: Int,
        @Query("pageSize") pageSize: Int,
        @Query("status") status: Int
    ): OrderStatusResponse

}