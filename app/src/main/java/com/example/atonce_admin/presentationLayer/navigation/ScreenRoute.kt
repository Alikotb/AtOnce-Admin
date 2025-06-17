package com.example.atonce_admin.presentationLayer.navigation

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

}