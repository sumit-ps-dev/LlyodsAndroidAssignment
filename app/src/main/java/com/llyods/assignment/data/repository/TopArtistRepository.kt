package com.llyods.assignment.data.repository

import com.llyods.assignment.data.mapper.ArtistMapper.toTopArtist
import com.llyods.assignment.data.network.IWebService
import com.llyods.assignment.data.response.ApiResult
import com.llyods.assignment.data.response.ApiResult.OnFailure
import com.llyods.assignment.data.response.ApiResult.OnSuccess
import com.llyods.assignment.data.response.ArtistsResponse
import com.llyods.assignment.domain.repository.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class TopArtistRepository @Inject constructor(
    override val webService: IWebService,
) : IRepository {

    override suspend fun getArtists(number: Int): Flow<ApiResult<ArtistsResponse>> =
            webService.getTopArtists(number)

}