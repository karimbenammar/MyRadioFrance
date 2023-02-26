package com.example.myradiofrance.domain.model

data class Shows(
    val edges: List<ShowEdge>
) {
    data class ShowEdge(
        val cursor: String?,
        val node: Show
    ) {
        data class Show(
            val id: String,
            val title: String,
            val url: String,
            val standFirst: String
        )
    }
}
