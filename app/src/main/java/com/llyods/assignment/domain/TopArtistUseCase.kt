package com.llyods.assignment.domain

import com.llyods.assignment.contract.IRepository
import com.llyods.assignment.contract.IUseCase
import com.llyods.assignment.data.ApiResult
import com.llyods.assignment.data.response.ArtistsResponse
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