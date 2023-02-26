package com.example.myradiofrance.presentation.shows

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.apollographql.apollo3.api.Optional
import com.example.myradiofrance.R
import com.example.myradiofrance.domain.model.Shows
import com.example.myradiofrance.presentation.components.ErrorMessage
import com.example.myradiofrance.presentation.components.Header
import com.example.myradiofrance.presentation.components.ProgressIndicator

@Composable
fun ShowsScreen(
    state: ShowsViewModel.ShowsState,
    onPaginate: (Optional<Int?>, Optional<String?>) -> Unit,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Header(navController = navController, title = stringResource(R.string.shows_title))

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(R.string.shows_prompt),
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        state.error?.let {
            ErrorMessage(message = it)
        }
        val infiniteListState = rememberLazyListState()
        state.shows?.let {
            ShowsList(
                state = state,
                infiniteListState = infiniteListState,
                shows = it,
                onPaginate = onPaginate
            )
        }
        if (state.isLoading) {
            ProgressIndicator()
        }
    }
}


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