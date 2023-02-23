package com.example.myradiofrance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myradiofrance.presentation.BrandsScreen
import com.example.myradiofrance.presentation.BrandsViewModel
import com.example.myradiofrance.ui.theme.MyRadioFranceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyRadioFranceTheme {
                val viewModel = hiltViewModel<BrandsViewModel>()
                val state = viewModel.state.collectAsState()
                BrandsScreen(state = state.value)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyRadioFranceTheme {
        Greeting("Android")
    }
}
