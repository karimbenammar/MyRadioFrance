package com.example.myradiofrance.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apollographql.apollo3.api.Optional
import com.example.myradiofrance.data.Shows
import com.example.myradiofrance.presentation.ShowsViewModel.Companion.ITEMS_LIMIT

@Composable
fun ShowsScreen(
    state: ShowsViewModel.ShowsState,
    onPaginate: (Optional<Int?>, Optional<String?>) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val infiniteListState = rememberLazyListState()
        LazyColumn(
            state = infiniteListState,
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.shows.edges) { showEdge ->
                ShowItem(show = showEdge.node)
            }
            if (state.isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
        if (infiniteListState.isScrolledToTheEnd() && !state.isLoading) {
            onPaginate(
                Optional.present(ITEMS_LIMIT),
                Optional.present(state.shows.edges.last().cursor)
            )
        }
    }
}

@Composable
fun ShowItem(show: Shows.ShowEdge.Show) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = show.title, fontSize = 10.sp)
    }
}

fun LazyListState.isScrolledToTheEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
