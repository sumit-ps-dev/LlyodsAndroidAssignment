package com.llyods.assignment.data.network


import com.llyods.assignment.data.response.ApiResult
import com.llyods.assignment.data.response.ArtistsResponse
import kotlinx.coroutines.flow.Flow


interface IWebService {

  suspend fun getTopArtists(number: Int): Flow<ApiResult<ArtistsResponse>>

}