package com.example.atonce_admin.data.remote.service

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.atonce_admin.data.remote.dto.LoginRequest
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertNull

@RunWith(AndroidJUnit4::class)
class AuthServiceTest : KoinTest {

    private val authService: AuthService by inject()

    @Test
    fun testLogin_RightEmail_RightPassword_Success() {
        runBlocking {
            //given
            val request = LoginRequest(
                email = "user@example.com",
                password = "test12345678"
            )
            //when
            val response = authService.login(request)
            //then
            assertNotNull(response.message, "Success message should not be null")
            assertNotNull(response.token, "Token should not be null")
            assertNotNull(response.representative.code, "Representative code should not be null")

        }
    }

    @Test
    fun testLogin_WrongEmail_RightPassword_Failure() {
        runBlocking {
            //given
            val request = LoginRequest(
                email = "wrong@example.com",
                password = "test12345678"
            )

            //when
            val response = authService.login(request)

            //then
            assertNotNull(response.message, "Failure message should not be null")
            assertNull(response.token, "Token should be null")
            assertNull(response.representative.code, "Representative code should be null")
        }
    }

    @Test
    fun testLogin_RightEmail_WrongPassword_Failure() {
        runBlocking {
            //given
            val request = LoginRequest(
                email = "user@example.com",
                password = "wrongPassword"
            )

            //when
            val response = authService.login(request)

            //then
            assertNotNull(response.message, "Failure message should not be null")
            assertNull(response.token, "Token should be null")
            assertNull(response.representative.code, "Representative code should be null")
        }
    }

    @Test
    fun testLogin_WrongEmail_WrongPassword_Failure() {
        runBlocking {
            //given
            val request = LoginRequest(
                email = "wrong@example.com",
                password = "wrongPassword"
            )

            //when
            val response = authService.login(request)

            //then
            assertNotNull(response.message, "Failure message should not be null")
            assertNull(response.token, "Token should be null")
            assertNull(response.representative.code, "Representative code should be null")
        }
    }
}