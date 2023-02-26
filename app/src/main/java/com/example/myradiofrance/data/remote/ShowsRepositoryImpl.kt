package com.example.myradiofrance.data.remote

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.example.ShowsQuery
import com.example.myradiofrance.data.mapper.toShows
import com.example.myradiofrance.domain.repository.ShowsRepository
import com.example.myradiofrance.domain.model.Shows
import com.example.type.StationsEnum

class ShowsRepositoryImpl(
    private val apolloClient: ApolloClient
) : ShowsRepository {

    override suspend fun getShows(
        station: String,
        limit: Optional<Int?>,
        after: Optional<String?>
    ): Shows {
        return apolloClient
            .query(ShowsQuery(StationsEnum.valueOf(station), limit, after))
            .execute()
            .data
            ?.shows
            ?.toShows()
            ?: Shows(emptyList())
    }
}
