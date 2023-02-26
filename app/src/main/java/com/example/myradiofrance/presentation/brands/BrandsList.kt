package com.example.myradiofrance.presentation.brands

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.myradiofrance.domain.model.Brand
import com.example.myradiofrance.navigation.Screen

@Composable
fun BrandsList(
    brands: List<Brand>,
    navController: NavController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(brands.size) { index ->
            val brand = brands[index]
            BrandItem(
                brand = brand,
                onClick = {
                    navController.navigate("${Screen.Shows.route}/${brand.id}")
                }
            )
        }
    }
}