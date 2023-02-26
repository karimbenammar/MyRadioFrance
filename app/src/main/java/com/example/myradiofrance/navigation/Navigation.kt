package com.example.myradiofrance.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myradiofrance.presentation.brands.BrandsScreen
import com.example.myradiofrance.presentation.brands.BrandsViewModel
import com.example.myradiofrance.presentation.shows.ShowsScreen
import com.example.myradiofrance.presentation.shows.ShowsViewModel
import com.example.myradiofrance.presentation.shows.ShowsViewModel.Companion.BRAND_ID_ARGUMENT

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Brands.route) {
        composable(Screen.Brands.route) {
            val viewModel = hiltViewModel<BrandsViewModel>()
            val state = viewModel.state.collectAsState()
            BrandsScreen(navController = navController, state = state.value)
        }
        composable(
            route = "${Screen.Shows.route}/{${BRAND_ID_ARGUMENT}}",
            arguments = listOf(navArgument(BRAND_ID_ARGUMENT) {
                type = NavType.StringType
            })
        ) {
            val viewModel = hiltViewModel<ShowsViewModel>()
            val state = viewModel.state.collectAsState()
            ShowsScreen(
                state = state.value,
                onPaginate = viewModel::fetchShows,
                navController = navController
            )
        }
    }
}
