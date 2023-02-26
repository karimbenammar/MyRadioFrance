package com.example.myradiofrance.data.remote

import com.apollographql.apollo3.ApolloClient
import com.example.BrandsQuery
import com.example.myradiofrance.data.mapper.toBrand
import com.example.myradiofrance.domain.repository.BrandRepository
import com.example.myradiofrance.domain.model.Brand

class BrandRepositoryImpl(
    private val apolloClient: ApolloClient
): BrandRepository {

    override suspend fun getBrands(): List<Brand> {
        return apolloClient
            .query(BrandsQuery())
            .execute()
            .data
            ?.brands
            ?.mapNotNull { it?.toBrand() }
            ?: emptyList()
    }
}
