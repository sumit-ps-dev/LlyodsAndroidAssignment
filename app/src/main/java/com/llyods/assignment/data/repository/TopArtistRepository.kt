package com.llyods.assignment.data.repository

import com.llyods.assignment.data.network.IWebService
import com.llyods.assignment.data.response.ApiResult
import com.llyods.assignment.data.response.ArtistsResponse
import com.llyods.assignment.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopArtistRepository @Inject constructor(
     val webService: IWebService,
) : IRepository {

    override suspend fun getArtists(number: Int): Flow<ApiResult<ArtistsResponse>> =
            webService.getTopArtists(number)

}