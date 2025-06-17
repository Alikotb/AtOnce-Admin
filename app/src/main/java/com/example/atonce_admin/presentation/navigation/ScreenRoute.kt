package com.example.atonce_admin.presentation.navigation

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
    object StateOrders : ScreenRoute()

    @Serializable
    object OrdersScreen : ScreenRoute()

    @Serializable
    object UsersScreen : ScreenRoute()


}