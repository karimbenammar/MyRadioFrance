package com.example.myradiofrance.domain.usecase

import com.apollographql.apollo3.api.Optional
import com.example.myradiofrance.domain.model.Shows
import com.example.myradiofrance.domain.repository.ShowsRepository
import com.example.myradiofrance.domain.util.Failure
import com.example.myradiofrance.domain.util.Resource

class GetShowsUseCase(
    private val showsRepository: ShowsRepository
) {

    suspend fun execute(
        station: String,
        limit: Optional<Int?>,
        after: Optional<String?>
    ): Resource<Shows, Failure.NetworkError> {
        return showsRepository.getShows(station, limit, after)
    }
}
