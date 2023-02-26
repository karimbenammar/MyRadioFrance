package com.example.myradiofrance.presentation.brands

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myradiofrance.R
import com.example.myradiofrance.domain.model.Brand
import com.example.myradiofrance.presentation.components.ErrorMessage
import com.example.myradiofrance.presentation.components.ProgressIndicator
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
            state.brands?.let {
                BrandsList(state.brands, navController)
            }
            state.error?.let {
                ErrorMessage(it)
            }
            if (state.isLoading) {
                ProgressIndicator()
            }
        }
    }
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
