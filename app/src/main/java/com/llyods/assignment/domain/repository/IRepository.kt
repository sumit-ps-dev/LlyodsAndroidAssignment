package com.llyods.assignment.domain.repository

import com.llyods.assignment.data.response.ApiResult
import com.llyods.assignment.data.response.ArtistsResponse
import kotlinx.coroutines.flow.Flow

interface IRepository {

    suspend fun getArtists(number: Int): Flow<ApiResult<ArtistsResponse>>
}