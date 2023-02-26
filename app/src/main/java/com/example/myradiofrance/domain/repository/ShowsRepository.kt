package com.example.myradiofrance.domain.repository

import com.apollographql.apollo3.api.Optional
import com.example.myradiofrance.domain.model.Shows

interface ShowsRepository {
    suspend fun getShows(
        station: String,
        limit: Optional<Int?>,
        after: Optional<String?>
    ): Shows
}
