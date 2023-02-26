package com.example.myradiofrance.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.example.ShowsQuery
import com.example.myradiofrance.domain.ShowsClient

class ApolloShowsClient(
    private val apolloClient: ApolloClient
) : ShowsClient {

    override suspend fun getShows(
        station: StationsEnum,
        limit: Optional<Int?>,
        after: Optional<String?>
    ): Shows {
        return apolloClient
            .query(ShowsQuery(station.toStationEnum(), limit, after))
            .execute()
            .data
            ?.shows
            ?.toShows()
            ?: Shows(emptyList())
    }
}
