package com.example.myradiofrance.domain.usecase

import com.example.myradiofrance.domain.model.Brand
import com.example.myradiofrance.domain.repository.BrandRepository

class GetBrandsUseCase(
    private val brandRepository: BrandRepository
) {

    suspend fun execute(): List<Brand> {
        return brandRepository.getBrands()
    }
}
