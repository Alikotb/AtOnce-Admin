package com.example.atonce_admin.presentation.common.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.atonce_admin.core.enums.OrderStatesEnum
import com.example.atonce_admin.domain.entity.OrderEntity
import com.example.atonce_admin.presentation.blogger.BloggerScreen
import com.example.atonce_admin.presentation.home.view.HomeWithDrawerScreen
import com.example.atonce_admin.presentation.login.LoginScreen
import com.example.atonce_admin.presentation.orders.OrdersScreen
import com.example.atonce_admin.presentation.pharmacyorders.PharmacyOrdersScreen
import com.example.atonce_admin.presentation.profile.view.ProfileScreen
import com.example.atonce_admin.presentation.splash.view.SplashScreen
import com.example.atonce_admin.presentation.stateOrders.view.StateOrders
import com.example.atonce_admin.presentation.users.model.CustomerModel
import com.example.atonce_admin.presentation.users.view.UsersScreen
import com.google.gson.Gson


@Composable
fun SetUpNavHost(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.SplashScreen
    ) {

        composable<ScreenRoute.SplashScreen> {
            SplashScreen {
                if (it) {
                    navController.navigate(ScreenRoute.HomeScreen) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                } else {
                    navController.navigate(ScreenRoute.LoginScreen) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
        }

        composable<ScreenRoute.LoginScreen> {
            LoginScreen(snackbarHostState = snackbarHostState) {
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
                onOrdersClicked = { orders, title ->
                    navController.navigate(
                        ScreenRoute.OrdersScreen(
                            Gson().toJson(orders),
                            title
                        )
                    )
                },
                onSeeMoreClick = {
                    navController.navigate(
                        ScreenRoute.StateOrdersScreen(
                            Gson().toJson(
                                OrderStatesEnum.ORDERED
                            )
                        )
                    )
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
            ProfileScreen(
                onLogout = {
                    navController.navigate(ScreenRoute.LoginScreen) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onContactClicked = { title, url ->
                    navController.navigate(ScreenRoute.BloggerScreen(title, url))
                }
            ) {
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
                onItemClick = { orders, title ->
                    navController.navigate(
                        ScreenRoute.OrdersScreen(
                            Gson().toJson(orders),
                            title
                        )

                    )
                }
            )
        }

        composable<ScreenRoute.OrdersScreen> {
            val orders = it.arguments?.getString("orders")
            val ordersList = Gson().fromJson(orders, Array<OrderEntity>::class.java).toList()
            val title = it.arguments?.getString("title") ?: ""
            OrdersScreen(
                orders = ordersList,
                title = title,
                onBackClicked = {
                    navController.navigateUp()
                }
            )
        }
        composable<ScreenRoute.UsersScreen> {
            UsersScreen(
                onItemClicked = {
                    navController.navigate(ScreenRoute.PharmacyOrdersScreen(Gson().toJson(it)))
                },
                onBackClicked = {
                    navController.navigateUp()
                }
            )
        }
        composable<ScreenRoute.BloggerScreen> {
            val title = it.arguments?.getString("title")
            val url = it.arguments?.getString("url")
            BloggerScreen(
                title = title ?: "",
                url = url ?: "",
                onBackClicked = {
                    navController.navigateUp()
                }
            )
        }

        composable<ScreenRoute.PharmacyOrdersScreen> {
            val pharmacy = it.arguments?.getString("pharmacy")
            val pharmacyModel = Gson().fromJson(pharmacy, CustomerModel::class.java)
            PharmacyOrdersScreen(
                pharmacy = pharmacyModel,
                onBackClicked = {
                    navController.navigateUp()
                }
            )
        }
    }
}