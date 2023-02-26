package com.example.myradiofrance.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import com.example.myradiofrance.navigation.Navigation
import com.example.myradiofrance.ui.theme.MyRadioFranceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            MyRadioFranceTheme {
                Surface {
                    Navigation(navController = navController)
                }
            }
        }
    }
}
