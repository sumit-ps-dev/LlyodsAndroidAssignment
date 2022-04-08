package com.llyods.assignment.domain

import com.llyods.assignment.contract.IRepository
import com.llyods.assignment.contract.IWebService
import com.llyods.assignment.data.ApiResult
import com.llyods.assignment.data.response.ArtistsResponse
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