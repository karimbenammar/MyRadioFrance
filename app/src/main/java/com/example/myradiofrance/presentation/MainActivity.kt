package com.example.myradiofrance.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.myradiofrance.navigation.Navigation
import com.example.myradiofrance.navigation.Screen
import com.example.myradiofrance.ui.theme.MyRadioFranceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen().also {
            it.setKeepOnScreenCondition {
                true
            }
        }

        setContent {
            val navController = rememberNavController()

            MyRadioFranceTheme {
                Surface {
                    LaunchedEffect(navController.currentBackStackEntry) {
                        navController.addOnDestinationChangedListener { _, destination, _ ->
                            if (destination.route == Screen.Brands.route) {
                                // Hide the splash screen after the start destination is fully loaded
                                splashScreen.setKeepOnScreenCondition {
                                    navController.currentDestination?.route != Screen.Brands.route
                                }
                            }
                        }
                    }

                    Navigation(navController = navController)
                }
            }
        }
    }
}
