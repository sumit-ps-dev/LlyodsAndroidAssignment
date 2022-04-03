package com.android.mvvm_cleanarchitecture.data.response

import com.squareup.moshi.Json

data class ArtistsResponse(
    @Json(name = "artists")
    val artists: Artists
)