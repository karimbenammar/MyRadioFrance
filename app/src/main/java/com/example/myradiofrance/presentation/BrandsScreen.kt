package com.example.myradiofrance.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myradiofrance.data.Brand

@Composable
fun BrandsScreen(
    state: BrandsViewModel.BrandsState
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(state.brands) { brand ->
                    BrandItem(brand = brand)
                }
            }
        }
    }
}

@Composable
fun BrandItem(brand: Brand) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = brand.title)
    }
}
