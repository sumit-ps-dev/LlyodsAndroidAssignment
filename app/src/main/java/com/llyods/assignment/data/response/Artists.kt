package com.llyods.assignment.data.response

import com.squareup.moshi.Json

data class Artists(
    @Json(name = "artist")
    val artist : List<Artist>
)