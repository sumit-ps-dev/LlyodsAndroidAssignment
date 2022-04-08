package com.llyods.assignment.data.response

import com.squareup.moshi.Json

data class ArtistsResponse(
    @Json(name = "artists")
    val artists: Artists
)