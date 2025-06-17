package com.example.atonce_admin.presentationLayer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.atonce_admin.presentationLayer.home.HomeScreen
import com.example.atonce_admin.presentationLayer.login.LoginScreen
import com.example.atonce_admin.presentationLayer.profile.ProfileScreen


@Composable
fun SetUpNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.LoginScreen
    ) {
        composable<ScreenRoute.LoginScreen> {
            LoginScreen(){
                navController.popBackStack()
                navController.navigate(ScreenRoute.HomeScreen)
            }
        }
        composable<ScreenRoute.HomeScreen> {
            HomeScreen(
                onDrawerClicked = {

                },
                onProfileClicked = {
                    navController.navigate(ScreenRoute.ProfileScreen)
                }
            )
        }
        composable<ScreenRoute.ProfileScreen> {
            ProfileScreen(){
                navController.navigate(ScreenRoute.HomeScreen)
            }
        }

    }
}