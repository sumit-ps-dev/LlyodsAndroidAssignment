package com.llyods.assignment.data.network

import com.llyods.assignment.data.response.ApiResult
import com.llyods.assignment.data.response.ArtistsResponse
import com.llyods.assignment.performNetworkApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitService @Inject constructor(private val client: ApiService) : IWebService {

    override suspend fun getTopArtists(number: Int): Flow<ApiResult<ArtistsResponse>> =
        performNetworkApiCall {
            client.getTopArtists(limit = number)
        }

}