package com.android.mvvm_cleanarchitecture.network

import com.android.mvvm_cleanarchitecture.data.response.ArtistsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

//    http://ws.audioscrobbler.com/2.0/?method=chart.gettopartists&api_key=52020c78a24e564bf9f7fd0f1ef43339&format=json


    @GET("/2.0/?method=chart.gettopartists")
    suspend fun getTopArtists(
        @Query("api_key") key: String = "52020c78a24e564bf9f7fd0f1ef43339",
        @Query("limit") limit: Int,
        @Query("format") format: String = "json"
    ): Response<ArtistsResponse>
}