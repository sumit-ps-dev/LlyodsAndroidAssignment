package com.android.mvvm_cleanarchitecture.data.response

import com.squareup.moshi.Json

data class Artist(
    @Json(name = "name")
    val name: String?,
    @Json(name = "playcount")
    val playcount: String?,
    @Json(name = "listeners")
    val listeners: String,
    @Json(name = "url")
    val url: String?

    )