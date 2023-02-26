package com.example.myradiofrance.domain.repository

import com.example.myradiofrance.domain.model.Brand
import com.example.myradiofrance.domain.util.Failure
import com.example.myradiofrance.domain.util.Resource

interface BrandRepository {
    suspend fun getBrands(): Resource<List<Brand>, Failure.NetworkError>
}
