package com.example.myradiofrance.domain.repository

import com.apollographql.apollo3.api.Optional
import com.example.myradiofrance.domain.model.Shows
import com.example.myradiofrance.domain.util.Failure
import com.example.myradiofrance.domain.util.Resource

interface ShowsRepository {
    suspend fun getShows(
        station: String,
        limit: Optional<Int?>,
        after: Optional<String?>
    ): Resource<Shows, Failure.NetworkError>
}
