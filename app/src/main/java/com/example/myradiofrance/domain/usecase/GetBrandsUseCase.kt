package com.example.myradiofrance.domain.usecase

import com.example.myradiofrance.domain.model.Brand
import com.example.myradiofrance.domain.repository.BrandRepository
import com.example.myradiofrance.domain.util.Failure
import com.example.myradiofrance.domain.util.Resource

class GetBrandsUseCase(
    private val brandRepository: BrandRepository
) {

    suspend fun execute(): Resource<List<Brand>, Failure.NetworkError> {
        return brandRepository.getBrands()
    }
}
