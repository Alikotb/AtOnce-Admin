package com.example.atonce_admin.data.remote.service

import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class PharmacyServiceTest : KoinTest {

    private val apiService : PharmacyService by inject()

    @Test
    fun getOrderDetails_withInvalidOrderId_Failure() {

    }
}