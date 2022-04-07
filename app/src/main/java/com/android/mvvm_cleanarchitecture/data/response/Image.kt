package com.android.mvvm_cleanarchitecture.data.response

import com.squareup.moshi.Json

data class Image(
    @field:Json(name = "#text")
    val text: String,
    @Json(name = "size")
    val size: String?,
)
