package com.example.myradiofrance.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.myradiofrance.Screen
import com.example.myradiofrance.data.Brand

@Composable
fun BrandsScreen(
    navController: NavController,
    state: BrandsViewModel.BrandsState
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(state.brands) { brand ->
                    BrandItem(
                        brand = brand,
                        onClick = {
                            navController.navigate("${Screen.Shows.route}/${brand.id}")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BrandItem(
    brand: Brand,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable(onClick = onClick)
    ) {
        Text(text = brand.title)
    }
}
