package com.android.mvvm_cleanarchitecture.network

import com.android.mvvm_cleanarchitecture.ApiResult
import com.android.mvvm_cleanarchitecture.contract.IWebService
import com.android.mvvm_cleanarchitecture.data.response.ArtistsResponse
import com.android.mvvm_cleanarchitecture.performNetworkApiCall
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