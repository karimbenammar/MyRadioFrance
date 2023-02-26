package com.example.myradiofrance.presentation.shows

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.apollographql.apollo3.api.Optional
import com.example.myradiofrance.domain.model.Shows
import com.example.myradiofrance.presentation.components.ProgressIndicator
import com.example.myradiofrance.presentation.shows.ShowsViewModel.ShowsState

@Composable
fun ShowsList(
    state: ShowsState,
    infiniteListState: LazyListState,
    shows: Shows,
    onPaginate: (Optional<Int?>, Optional<String?>) -> Unit
) {
    LazyColumn(
        state = infiniteListState,
        modifier = Modifier.fillMaxSize()
    ) {
        items(shows.edges) { showEdge ->
            ShowItem(show = showEdge.node)
        }
        if (state.isLoading) {
            item {
                ProgressIndicator()
            }
        }
    }
    if (infiniteListState.isScrolledToTheEnd() && !state.isLoading) {
        onPaginate(
            Optional.present(ShowsViewModel.ITEMS_LIMIT),
            Optional.present(shows.edges.last().cursor)
        )
    }
}

fun LazyListState.isScrolledToTheEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1