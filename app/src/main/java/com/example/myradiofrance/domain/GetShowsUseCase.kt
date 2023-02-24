package com.example.myradiofrance.domain

import com.apollographql.apollo3.api.Optional
import com.example.myradiofrance.data.Shows
import com.example.type.StationsEnum
import kotlinx.coroutines.delay

class GetShowsUseCase(
    private val showsClient: ShowsClient
) {

    suspend fun execute(
        station: StationsEnum,
        limit: Optional<Int?>,
        after: Optional<String?>
    ): Shows {
        return showsClient.getShows(station, limit, after)
    }
}
