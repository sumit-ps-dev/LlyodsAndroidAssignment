package com.llyods.assignment.data.response

import com.squareup.moshi.Json

data class Image(
    @field:Json(name = "#text")
    val text: String,
    @Json(name = "size")
    val size: String?,
)
