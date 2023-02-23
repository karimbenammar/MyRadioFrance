package com.example.myradiofrance.domain

import com.example.myradiofrance.data.Brand
import com.example.myradiofrance.data.Shows
import com.example.myradiofrance.data.StationsEnum

interface BrandClient {
    suspend fun getBrands(): List<Brand>
    suspend fun getShows(station: StationsEnum, limit: Int): Shows
}
