package com.android.mvvm_cleanarchitecture.contract


import com.android.mvvm_cleanarchitecture.data.ApiResult
import com.android.mvvm_cleanarchitecture.data.response.ArtistsResponse
import kotlinx.coroutines.flow.Flow


interface IWebService {

  suspend fun getTopArtists(number: Int): Flow<ApiResult<ArtistsResponse>>

}