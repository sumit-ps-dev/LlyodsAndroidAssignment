package com.llyods.assignment.domain.repository

import com.llyods.assignment.data.network.IWebService
import com.llyods.assignment.data.response.ApiResult
import com.llyods.assignment.domain.model.Artist
import kotlinx.coroutines.flow.Flow

interface IRepository {

    val webService: IWebService

    suspend fun getArtists(number: Int): Flow<ApiResult<List<Artist>>>
}