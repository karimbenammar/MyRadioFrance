package com.example.myradiofrance.data.mapper

import com.example.BrandsQuery
import com.example.myradiofrance.domain.model.Brand

fun BrandsQuery.Brand.toBrand(): Brand {
    return Brand(
        id = id,
        title = title,
        baseline = baseline ?: "",
        description = description ?: "",
        websiteUrl = websiteUrl ?: "",
        liveStream = liveStream ?: ""
    )
}
