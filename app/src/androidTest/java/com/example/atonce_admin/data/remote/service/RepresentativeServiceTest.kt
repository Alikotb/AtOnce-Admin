package com.example.atonce_admin.data.remote.service

import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertNotEquals

@RunWith(AndroidJUnit4::class)
class RepresentativeServiceTest : KoinTest {

    private val representativeService: RepresentativeService by inject()

    @Test
    fun testGetOrderStates_WithExitOrderId_ReturnsOrderStates() {
        runBlocking {
            //given
            val orderId = 1

            //when
            val orderStates = representativeService.getOrdersStats(orderId)

            //then
            assertNotNull(orderStates)
            assertNotEquals(orderStates.pharmacyCount, 0)
            assertNotEquals(orderStates.stats.Ordered, 0)
            assertNotEquals(orderStates.revenue, 0.0)
        }
    }

    @Test
    fun testGetOrderStates_WithNonExistentOrderId_ReturnsEmptyList() {
        runBlocking {
            //given
            val orderId = 9999

            //when
            val orderStates = representativeService.getOrdersStats(orderId)

            //then
            assertNotNull(orderStates)
            assertEquals(orderStates.pharmacyCount, 0)
            assertEquals(orderStates.stats.Ordered, 0)
            assertEquals(orderStates.revenue, 0.0)
        }
    }

    @Test
    fun testOrderDetails_WithExitRepresentativeId_ReturnsOrderDetails() {
        runBlocking {
            //given
            val representativeId = 1
            val pageNumber = 1
            val pageSize = 10
            val status = 1

            //when
            val orderDetails = representativeService.getOrdersByStatus(representativeId, pageNumber, pageSize, status)

            //then
            assertNotNull(orderDetails)
            assertNotEquals(orderDetails.items.size, 0)
            assertNotEquals(orderDetails.pageNumber, 0)
            assertNotEquals(orderDetails.items[0].orders.size, 0)
            assertNotEquals(orderDetails.totalCount, 0)
        }
    }

    @Test
    fun testOrderDetails_WithNonExitRepresentativeId_ReturnsOrderDetails() {
        runBlocking {
            //given
            val representativeId = 100
            val pageNumber = 1
            val pageSize = 10
            val status = 1

            //when
            val orderDetails = representativeService.getOrdersByStatus(representativeId, pageNumber, pageSize, status)

            //then
            assertNotNull(orderDetails)
            assertEquals(orderDetails.items.size, 0)
            assertEquals(orderDetails.totalCount, 0)
        }
    }

    @Test
    fun testGetAllCustomers_WithExitRepresentativeId_ReturnsCustomerResponse() {
        runBlocking {
            //given
            val representativeId = 1

            //when
            val customerResponse = representativeService.getAllCustomers(representativeId)

            //then
            assertNotNull(customerResponse)
            assertEquals(customerResponse.representativeId, representativeId)
            assertNotEquals(customerResponse.representativeName, "")
        }
    }

    @Test
    fun testGetAllCustomers_WithNonExitRepresentativeId_ReturnsCustomerResponse() {
        runBlocking {
            //given
            val representativeId = 100

            //when
            val customerResponse = representativeService.getAllCustomers(representativeId)

            //then
            assertNotNull(customerResponse)
            assertEquals(customerResponse.representativeId, representativeId)
            assertEquals(customerResponse.representativeName, "")
        }
    }
}