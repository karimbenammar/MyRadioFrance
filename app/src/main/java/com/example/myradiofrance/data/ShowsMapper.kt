package com.example.myradiofrance.data

import com.example.ShowsQuery

fun ShowsQuery.Shows.toShows(): Shows {
    return Shows(
        edges = edges?.mapNotNull { it?.toEdge() } ?: emptyList()
    )
}

fun ShowsQuery.Edge.toEdge(): Shows.ShowEdge {
    return Shows.ShowEdge(
        cursor = cursor,
        node = node?.toShow() ?: Shows.ShowEdge.Show("", "", "")
    )
}

fun ShowsQuery.Node.toShow(): Shows.ShowEdge.Show {
    return Shows.ShowEdge.Show(
        id = id,
        title = title,
        url = url?: ""
    )
}
