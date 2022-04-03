package com.android.mvvm_cleanarchitecture.domain

import com.android.mvvm_cleanarchitecture.ApiResult
import com.android.mvvm_cleanarchitecture.contract.IUseCase
import com.android.mvvm_cleanarchitecture.contract.IWebService
import com.android.mvvm_cleanarchitecture.data.response.ArtistsResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopArtistUseCase @Inject constructor(
    private val service: IWebService,
) : IUseCase<Int, ArtistsResponse> {

    override suspend fun execute(input: Int): Flow<ApiResult<ArtistsResponse>> =
        service.getTopArtists(input)


}