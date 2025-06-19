package com.example.atonce_admin.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.atonce_admin.presentation.enums.OrderStatesEnum
import com.example.atonce_admin.presentation.orders.OrdersScreen
import com.example.atonce_admin.presentation.home.HomeScreen
import com.example.atonce_admin.presentation.home.HomeWithDrawerScreen
import com.example.atonce_admin.presentation.login.LoginScreen
import com.example.atonce_admin.presentation.profile.ProfileScreen
import com.example.atonce_admin.presentation.stateOrders.StateOrders
import com.example.atonce_admin.presentation.users.UsersScreen
import com.google.gson.Gson


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
                navController.navigate(ScreenRoute.HomeScreen) {
                    popUpTo(0) { inclusive = true }
                    launchSingleTop = true
                }

            }
        }
        composable<ScreenRoute.HomeScreen> {
            HomeWithDrawerScreen(
                onProfileClicked = {
                    navController.navigate(ScreenRoute.ProfileScreen)
                },
                onOrdersClicked = {
                    navController.navigate(ScreenRoute.OrdersScreen(
                        Gson().toJson(OrderStatesEnum.ORDERED)
                    ))
                },
                onSeeMoreClick = {
                    navController.navigate(ScreenRoute.StateOrdersScreen(Gson().toJson(OrderStatesEnum.ORDERED)))
                },
                onLogout = {
                    navController.navigate(ScreenRoute.LoginScreen) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onCustomerClicked = {
                    navController.navigate(ScreenRoute.UsersScreen)
                }, onItemClicked = {
                    navController.navigate(ScreenRoute.StateOrdersScreen(Gson().toJson(it)))
                }
            )
        }
        composable<ScreenRoute.ProfileScreen> {
            ProfileScreen(onLogout = {
                navController.navigate(ScreenRoute.LoginScreen) {
                    popUpTo(0) { inclusive = true }
                    launchSingleTop = true
                }
            }){
                navController.navigateUp()
            }
        }

        composable<ScreenRoute.StateOrdersScreen> {
            val type = it.arguments?.getString("type")
            val state = Gson().fromJson(type, OrderStatesEnum::class.java)
            StateOrders(
                type = state,
                onBackClicked = {
                    navController.navigateUp()
                },
                onItemClick = {
                    navController.navigate(ScreenRoute.OrdersScreen(
                        Gson().toJson(it)
                    ))
                }
            )
        }

        composable<ScreenRoute.OrdersScreen> {
            val type = it.arguments?.getString("type")
            val state = Gson().fromJson(type, OrderStatesEnum::class.java)
            OrdersScreen(
                type = state,
                onBackClicked = {
                    navController.navigateUp()
                }
            )
        }
        composable<ScreenRoute.UsersScreen> {
            UsersScreen(
                onBackClicked = {
                    navController.navigateUp()
                }
            )
        }
    }
}