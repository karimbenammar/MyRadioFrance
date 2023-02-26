package com.example.myradiofrance.domain.repository

import com.example.myradiofrance.domain.model.Brand

interface BrandRepository {
    suspend fun getBrands(): List<Brand>
}
