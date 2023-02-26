package com.example.myradiofrance.data.remote

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.example.BrandsQuery
import com.example.myradiofrance.data.mapper.toBrand
import com.example.myradiofrance.domain.model.Brand
import com.example.myradiofrance.domain.repository.BrandRepository
import com.example.myradiofrance.domain.util.Failure
import com.example.myradiofrance.domain.util.Resource

class BrandRepositoryImpl(
    private val apolloClient: ApolloClient
) : BrandRepository {

    override suspend fun getBrands(): Resource<List<Brand>, Failure.NetworkError> {
        return try {
            Resource.Success(
                apolloClient.query(BrandsQuery())
                    .execute().data?.brands?.mapNotNull { it?.toBrand() } ?: emptyList())
        } catch (exception: ApolloException) {
            Resource.Error(Failure.NetworkError(exception.localizedMessage ?: "Unknown error"))
        }
    }
}
