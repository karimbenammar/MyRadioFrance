package com.example.myradiofrance.data

import com.apollographql.apollo3.ApolloClient
import com.example.BrandsQuery
import com.example.myradiofrance.domain.BrandClient

class ApolloBrandClient(
    private val apolloClient: ApolloClient
): BrandClient {

    override suspend fun getBrands(): List<Brand> {
        return apolloClient
            .query(BrandsQuery())
            .execute()
            .data
            ?.brands
            ?.mapNotNull { it?.toBrand() }
            ?: emptyList()
    }

    override suspend fun getShows(station: StationsEnum, limit: Int): Shows {
        TODO("Not yet implemented")
    }
}
