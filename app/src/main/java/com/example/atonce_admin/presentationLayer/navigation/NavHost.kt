package com.example.atonce_admin.presentationLayer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.atonce_admin.presentationLayer.home.HomeScreen
import com.example.atonce_admin.presentationLayer.login.LoginScreen
import com.example.atonce_admin.presentationLayer.profile.ProfileScreen
import com.example.atonce_admin.presentationLayer.stateOrders.StateOrders


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
                },
                onSeeMoreClick = {
                    navController.navigate(ScreenRoute.StateOrders)
                }
            )
        }
        composable<ScreenRoute.ProfileScreen> {
            ProfileScreen(){
                navController.navigateUp()
            }
        }

        composable<ScreenRoute.StateOrders> {
            StateOrders(
                onBackClicked = {
                    navController.navigateUp()
                }
            )
        }

    }
}