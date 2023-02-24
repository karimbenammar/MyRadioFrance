package com.example.myradiofrance

sealed class Screen(val route: String) {
    object Brands : Screen("brands_screen")
    object Shows : Screen("shows_screen")
}
