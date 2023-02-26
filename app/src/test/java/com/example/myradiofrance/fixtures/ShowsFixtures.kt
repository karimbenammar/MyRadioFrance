package com.example.myradiofrance.fixtures

import com.example.myradiofrance.domain.model.Shows

object ShowsFixtures {
    val showsResult = Shows(
        listOf(
            Shows.ShowEdge(
                cursor = "1",
                node = Shows.ShowEdge.Show(
                    id = "1",
                    title = "Show 1",
                    url = "",
                    standFirst = "Show 1 description"
                )
            ),
            Shows.ShowEdge(
                cursor = "2",
                node = Shows.ShowEdge.Show(
                    id = "2",
                    title = "Show 2",
                    url = "",
                    standFirst = "Show 2 description"
                )
            ),
            Shows.ShowEdge(
                cursor = "3",
                node = Shows.ShowEdge.Show(
                    id = "3",
                    title = "Show 3",
                    url = "",
                    standFirst = "Show 3 description"
                )
            )
        )
    )

    val extraShowsResult = Shows(
        listOf(
            Shows.ShowEdge(
                cursor = "4",
                node = Shows.ShowEdge.Show(
                    id = "4",
                    title = "Show 4",
                    url = "",
                    standFirst = "Show 4 description"
                )
            ),
            Shows.ShowEdge(
                cursor = "5",
                node = Shows.ShowEdge.Show(
                    id = "5",
                    title = "Show 5",
                    url = "",
                    standFirst = "Show 5 description"
                )
            ),
            Shows.ShowEdge(
                cursor = "6",
                node = Shows.ShowEdge.Show(
                    id = "6",
                    title = "Show 6",
                    url = "",
                    standFirst = "Show 6 description"
                )
            )
        )
    )

    val mergedShowsResult = Shows(
        listOf(
            Shows.ShowEdge(
                cursor = "1",
                node = Shows.ShowEdge.Show(
                    id = "1",
                    title = "Show 1",
                    url = "",
                    standFirst = "Show 1 description"
                )
            ),
            Shows.ShowEdge(
                cursor = "2",
                node = Shows.ShowEdge.Show(
                    id = "2",
                    title = "Show 2",
                    url = "",
                    standFirst = "Show 2 description"
                )
            ),
            Shows.ShowEdge(
                cursor = "3",
                node = Shows.ShowEdge.Show(
                    id = "3",
                    title = "Show 3",
                    url = "",
                    standFirst = "Show 3 description"
                )
            ),
            Shows.ShowEdge(
                cursor = "4",
                node = Shows.ShowEdge.Show(
                    id = "4",
                    title = "Show 4",
                    url = "",
                    standFirst = "Show 4 description"
                )
            ),
            Shows.ShowEdge(
                cursor = "5",
                node = Shows.ShowEdge.Show(
                    id = "5",
                    title = "Show 5",
                    url = "",
                    standFirst = "Show 5 description"
                )
            ),
            Shows.ShowEdge(
                cursor = "6",
                node = Shows.ShowEdge.Show(
                    id = "6",
                    title = "Show 6",
                    url = "",
                    standFirst = "Show 6 description"
                )
            )
        )
    )
}