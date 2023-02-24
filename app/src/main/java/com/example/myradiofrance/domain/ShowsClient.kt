package com.example.myradiofrance.domain

import com.apollographql.apollo3.api.Optional
import com.example.myradiofrance.data.Shows
import com.example.type.StationsEnum

interface ShowsClient {
    suspend fun getShows(station: StationsEnum, limit: Optional<Int?>, after: Optional<String?>): Shows
}
