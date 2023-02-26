package com.example.myradiofrance.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.Optional
import com.example.myradiofrance.domain.usecase.GetShowsUseCase
import com.example.myradiofrance.domain.model.Shows
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowsViewModel @Inject constructor(
    private val getShowsUseCase: GetShowsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(ShowsState())
    val state = _state.asStateFlow()
    val station = savedStateHandle.get<String>(BRAND_ID_ARGUMENT) ?: UNKNOWN_STATION

    init {
        fetchShows(Optional.present(ITEMS_LIMIT), Optional.absent())
    }

    fun fetchShows(limit: Optional<Int?>, after: Optional<String?>) {
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
        const val BRAND_ID_ARGUMENT = "brandId"
        const val UNKNOWN_STATION = "UNKNOWN__"
    }
}
