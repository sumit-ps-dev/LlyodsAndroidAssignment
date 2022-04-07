package com.android.mvvm_cleanarchitecture.domain

import com.android.mvvm_cleanarchitecture.contract.IRepository
import com.android.mvvm_cleanarchitecture.contract.IUseCase
import com.android.mvvm_cleanarchitecture.data.ApiResult
import com.android.mvvm_cleanarchitecture.data.response.ArtistsResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopArtistUseCase @Inject constructor(
    override val repository: IRepository,
) : IUseCase<Int, ArtistsResponse> {

    override suspend fun execute(input: Int): Flow<ApiResult<ArtistsResponse>> =
        repository.getTopArtists(input)

}