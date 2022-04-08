package com.llyods.assignment.contract

import com.llyods.assignment.data.ApiResult
import com.llyods.assignment.data.response.ArtistsResponse
import kotlinx.coroutines.flow.Flow

interface IRepository {

    val webService: IWebService

    suspend fun getTopArtists(number: Int): Flow<ApiResult<ArtistsResponse>>
}