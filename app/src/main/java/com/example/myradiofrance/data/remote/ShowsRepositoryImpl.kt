package com.example.myradiofrance.data.remote

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.exception.ApolloException
import com.example.ShowsQuery
import com.example.myradiofrance.data.mapper.toShows
import com.example.myradiofrance.domain.model.Shows
import com.example.myradiofrance.domain.repository.ShowsRepository
import com.example.myradiofrance.domain.util.Failure
import com.example.myradiofrance.domain.util.Resource
import com.example.type.StationsEnum

class ShowsRepositoryImpl(
    private val apolloClient: ApolloClient
) : ShowsRepository {

    override suspend fun getShows(
        station: String, limit: Optional<Int?>, after: Optional<String?>
    ): Resource<Shows, Failure.NetworkError> {
        return try {
            Resource.Success(
                apolloClient.query(ShowsQuery(StationsEnum.valueOf(station), limit, after))
                    .execute().data?.shows?.toShows() ?: Shows(emptyList())
            )
        } catch (exception: ApolloException) {
            Resource.Error(Failure.NetworkError(exception.localizedMessage ?: "Unknown error"))
        }
    }
}
