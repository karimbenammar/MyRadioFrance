package com.example.myradiofrance

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myradiofrance.presentation.BrandsScreen
import com.example.myradiofrance.presentation.BrandsViewModel
import com.example.myradiofrance.presentation.ShowsScreen
import com.example.myradiofrance.presentation.ShowsViewModel
import com.example.myradiofrance.presentation.ShowsViewModel.Companion.BRAND_ID_ARGUMENT
import com.example.type.StationsEnum

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Brands.route) {
        composable(Screen.Brands.route) {
            val viewModel = hiltViewModel<BrandsViewModel>()
            val state = viewModel.state.collectAsState()
            BrandsScreen(navController, state.value)
        }
        composable(
            route = "${Screen.Shows.route}/{${BRAND_ID_ARGUMENT}}",
            arguments = listOf(navArgument(BRAND_ID_ARGUMENT) {
                type = NavType.EnumType(StationsEnum::class.java)
            })
        ) {
            val viewModel = hiltViewModel<ShowsViewModel>()
            val state = viewModel.state.collectAsState()
            ShowsScreen(state.value, viewModel::fetchShows, navController)
        }
    }
}
