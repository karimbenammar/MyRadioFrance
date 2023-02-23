package com.example.myradiofrance.data

import com.example.BrandsQuery

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
