package com.example.myradiofrance.domain.usecase

import com.apollographql.apollo3.api.Optional
import com.example.myradiofrance.domain.model.Shows
import com.example.myradiofrance.domain.repository.ShowsRepository

class GetShowsUseCase(
    private val showsRepository: ShowsRepository
) {

    suspend fun execute(
        station: String,
        limit: Optional<Int?>,
        after: Optional<String?>
    ): Shows {
        return showsRepository.getShows(station, limit, after)
    }
}
