package com.example.myradiofrance.domain

import com.example.myradiofrance.data.Brand

interface BrandClient {
    suspend fun getBrands(): List<Brand>
}
