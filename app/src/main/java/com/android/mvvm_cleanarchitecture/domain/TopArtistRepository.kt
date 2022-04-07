package com.android.mvvm_cleanarchitecture.domain

import com.android.mvvm_cleanarchitecture.contract.IRepository
import com.android.mvvm_cleanarchitecture.contract.IWebService
import com.android.mvvm_cleanarchitecture.data.ApiResult
import com.android.mvvm_cleanarchitecture.data.response.ArtistsResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopArtistRepository @Inject constructor(
    override val webService: IWebService,
) : IRepository {

    override suspend fun getTopArtists(number: Int): Flow<ApiResult<ArtistsResponse>> =
        webService.getTopArtists(number)

}