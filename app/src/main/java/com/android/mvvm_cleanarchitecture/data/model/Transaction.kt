package com.android.mvvm_cleanarchitecture.data.model

import com.squareup.moshi.Json

data class Transaction(
    val id: Int,
    val description: String,
    val amount: Double,
    val effectiveDate: String
)
