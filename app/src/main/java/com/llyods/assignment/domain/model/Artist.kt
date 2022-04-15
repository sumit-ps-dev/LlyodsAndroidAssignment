package com.llyods.assignment.domain.model

import com.llyods.assignment.data.response.Image

data class Artist(
    val name: String?,
    val playcount: String?,
    val listeners: String?,
    val url: String?,
    val image: List<Image>
)
