package com.example.myradiofrance.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.Optional
import com.example.myradiofrance.data.Shows
import com.example.myradiofrance.domain.GetShowsUseCase
import com.example.type.StationsEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowsViewModel @Inject constructor(
    private val getShowsUseCase: GetShowsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ShowsState())
    val state = _state.asStateFlow()

    init {
        fetchShows(StationsEnum.FRANCEINTER, Optional.present(ITEMS_LIMIT), Optional.absent())
    }

    fun fetchShows(station: StationsEnum, limit: Optional<Int?>, after: Optional<String?>) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            _state.update {
                it.copy(
                    shows = it.shows.copy(
                        edges = it.shows.edges + getShowsUseCase.execute(
                            station,
                            limit,
                            after
                        ).edges
                    ),
                    isLoading = false
                )
            }
        }
    }

    data class ShowsState(
        val isLoading: Boolean = false,
        val shows: Shows = Shows(emptyList())
    )

    companion object {
        const val ITEMS_LIMIT = 5
    }
}
