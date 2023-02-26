package com.example.myradiofrance.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.apollographql.apollo3.api.Optional
import com.example.myradiofrance.R
import com.example.myradiofrance.domain.model.Shows
import com.example.myradiofrance.presentation.component.HeaderScreen
import com.example.myradiofrance.presentation.component.ShowItem
import com.example.myradiofrance.presentation.viewmodel.ShowsViewModel
import com.example.myradiofrance.presentation.viewmodel.ShowsViewModel.Companion.ITEMS_LIMIT

@Composable
fun ShowsScreen(
    state: ShowsViewModel.ShowsState,
    onPaginate: (Optional<Int?>, Optional<String?>) -> Unit,
    navController: NavController
) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            HeaderScreen(navController = navController, title = stringResource(R.string.shows_title))

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = stringResource(R.string.shows_prompt),
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

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
}

fun LazyListState.isScrolledToTheEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ShowsScreenPreview() {
    ShowsScreen(
        state = ShowsViewModel.ShowsState(
            shows = Shows(
                edges = listOf(
                    Shows.ShowEdge(
                        cursor = "cursor",
                        node = Shows.ShowEdge.Show(
                            id = "001179a9-fe05-4a03-a0a0-7c360539db54_4",
                            title = "MAXXI Classique",
                            url = "https://www.francemusique.fr/francemusique/podcasts/maxxi-classique",
                            standFirst = "Un récit en musique où le classique dialogue avec d'autres esthétiques mais aussi le cinéma, les jeux vidéos, la télévision, la scène et la littérature..."
                        )
                    ),
                    Shows.ShowEdge(
                        cursor = "cursor",
                        node = Shows.ShowEdge.Show(
                            id = "001179a9-fe05-4a03-a0a0-7c360539db54_4",
                            title = "MAXXI Classique",
                            url = "https://www.francemusique.fr/francemusique/podcasts/maxxi-classique",
                            standFirst = ""
                        )
                    ),
                    Shows.ShowEdge(
                        cursor = "cursor",
                        node = Shows.ShowEdge.Show(
                            id = "001179a9-fe05-4a03-a0a0-7c360539db54_4",
                            title = "MAXXI Classique",
                            url = "https://www.francemusique.fr/francemusique/podcasts/maxxi-classique",
                            standFirst = "Un récit en musique où le classique dialogue avec d'autres esthétiques mais aussi le cinéma, les jeux vidéos, la télévision, la scène et la littérature..."
                        )
                    ),
                )
            ),
            isLoading = true
        ),
        onPaginate = { _, _ -> },
        navController = rememberNavController()
    )
}