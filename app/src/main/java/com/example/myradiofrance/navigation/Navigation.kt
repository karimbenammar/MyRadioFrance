package com.example.myradiofrance.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myradiofrance.presentation.screen.BrandsScreen
import com.example.myradiofrance.presentation.viewmodel.BrandsViewModel
import com.example.myradiofrance.presentation.screen.ShowsScreen
import com.example.myradiofrance.presentation.viewmodel.ShowsViewModel
import com.example.myradiofrance.presentation.viewmodel.ShowsViewModel.Companion.BRAND_ID_ARGUMENT
import com.example.myradiofrance.presentation.screen.SplashScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
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
