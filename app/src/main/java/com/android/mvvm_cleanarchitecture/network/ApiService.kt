package com.android.mvvm_cleanarchitecture.network

import com.android.mvvm_cleanarchitecture.BuildConfig
import com.android.mvvm_cleanarchitecture.data.response.ArtistsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/2.0/?method=chart.gettopartists")
    suspend fun getTopArtists(
        @Query("api_key") key: String = BuildConfig.API_KEY,
        @Query("limit") limit: Int = 30,
        @Query("format") format: String = "json"
    ): Response<ArtistsResponse>
}