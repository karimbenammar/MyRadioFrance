package com.example.myradiofrance.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myradiofrance.R
import com.example.myradiofrance.Screen
import com.example.myradiofrance.data.Brand
import com.example.myradiofrance.ui.theme.Blue
import com.example.myradiofrance.ui.theme.BlueDark
import com.example.myradiofrance.ui.theme.BlueDarkest
import com.example.myradiofrance.ui.theme.MyRadioFranceTheme

@Composable
fun BrandsScreen(
    navController: NavController,
    state: BrandsViewModel.BrandsState
) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.stations_title),
                style = typography.h4
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.stations_prompt),
                style = typography.body2
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (state.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(state.brands.size) { index ->
                        val brand = state.brands[index]
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
}

@Composable
fun BrandItem(
    brand: Brand,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .aspectRatio(1.9f)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize(),
            elevation = 2.dp,
            shape = RoundedCornerShape(corner = CornerSize(6.dp))
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.stations_bg),
                contentDescription = null
            )
            Row(
                modifier = Modifier
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                BlueDarkest.copy(alpha = 0.8f),
                                BlueDark.copy(alpha = 0.8f),
                                Blue.copy(alpha = 0.8f)
                            ),
                            start = Offset(0f, Float.POSITIVE_INFINITY),
                            end = Offset(Float.POSITIVE_INFINITY, 0f)
                        )
                    )
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterVertically),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp),
                        painter = painterResource(id = R.drawable.baseline_radio_24),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = brand.title,
                        style = typography.button,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun BrandItemPreview() {
    BrandItem(
        brand = Brand("FRANCEINTER", "France Inter", "", "", "", ""),
        onClick = {}
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun BrandsScreenLoadingPreview() {
    MyRadioFranceTheme {
        BrandsScreen(
            navController = rememberNavController(),
            state = BrandsViewModel.BrandsState(
                isLoading = true
            )
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun BrandsScreenPreview() {
    BrandsScreen(
        navController = rememberNavController(),
        state = BrandsViewModel.BrandsState(
            isLoading = false,
            brands = listOf(
                Brand("FRANCEINTER", "France Inter", "", "", "", ""),
                Brand("FRANCEINFO", "Franceinfo", "", "", "", ""),
                Brand("FRANCEMUSIQUE", "France Musique", "", "", "", ""),
                Brand("FRANCECULTURE", "France Culture", "", "", "", ""),
                Brand("MOUV", "Mouv'", "", "", "", ""),
                Brand("FIP", "FIP", "", "", "", ""),
                Brand("FRANCEBLEU", "France Bleu", "", "", "", ""),
            )
        )
    )
}
