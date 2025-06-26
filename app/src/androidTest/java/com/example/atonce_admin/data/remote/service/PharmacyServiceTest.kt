package com.example.atonce_admin.data.remote.service

import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertEquals

class PharmacyServiceTest : KoinTest {

    private val apiService : PharmacyService by inject()

    @Test
    fun getOrderDetails_withInvalidOrderId_Failure() {
        runBlocking {
            //given
            val orderId = -1

            //when
            val response = apiService.getOrderDetails(orderId)

            //then
            assert(response.result.isEmpty())
            assertEquals(response.message, "success")

        }
    }
}