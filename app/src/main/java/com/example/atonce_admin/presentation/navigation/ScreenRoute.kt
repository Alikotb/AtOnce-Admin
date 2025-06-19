package com.example.atonce_admin.presentation.navigation

import com.example.atonce_admin.presentation.enums.OrderStatesEnum
import kotlinx.serialization.Serializable


sealed class ScreenRoute {
    @Serializable
    object SplashScreen : ScreenRoute()

    @Serializable
    object LoginScreen : ScreenRoute()

    @Serializable
    object HomeScreen : ScreenRoute()

    @Serializable
    object ProfileScreen : ScreenRoute()

    @Serializable
    data class StateOrdersScreen(val type : String) : ScreenRoute()

    @Serializable
    data class OrdersScreen(val type : String) : ScreenRoute()

    @Serializable
    object UsersScreen : ScreenRoute()


}