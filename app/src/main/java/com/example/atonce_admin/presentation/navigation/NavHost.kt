package com.example.atonce_admin.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.atonce_admin.presentation.orders.OrdersScreen
import com.example.atonce_admin.presentation.home.HomeScreen
import com.example.atonce_admin.presentation.login.LoginScreen
import com.example.atonce_admin.presentation.profile.ProfileScreen
import com.example.atonce_admin.presentation.stateOrders.StateOrders


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
                onOrdersClicked = {
                    navController.navigate(ScreenRoute.OrdersScreen)
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
                },
                onItemClick = {
                    navController.navigate(ScreenRoute.OrdersScreen)
                }
            )
        }

        composable<ScreenRoute.OrdersScreen> {
            OrdersScreen(
                onBackClicked = {
                    navController.navigateUp()
                }
            )
        }


    }
}