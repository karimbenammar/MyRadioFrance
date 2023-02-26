package com.example.myradiofrance.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Brands : Screen("brands_screen")
    object Shows : Screen("shows_screen")
}
