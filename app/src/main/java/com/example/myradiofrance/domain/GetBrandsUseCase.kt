package com.example.myradiofrance.domain

import com.example.myradiofrance.data.Brand

class GetBrandsUseCase(
    private val brandClient: BrandClient
) {

    suspend fun execute(): List<Brand> {
        return brandClient.getBrands()
    }
}
