package com.llyods.assignment.data.repository

import com.llyods.assignment.data.mapper.ArtistMapper.toTopArtist
import com.llyods.assignment.data.network.IWebService
import com.llyods.assignment.data.response.ApiResult
import com.llyods.assignment.data.response.ArtistsResponse
import com.llyods.assignment.domain.model.Artist
import com.llyods.assignment.domain.repository.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class TopArtistRepository @Inject constructor(
    override val webService: IWebService,
) : IRepository {

    override suspend fun getArtists(number: Int): Flow<ApiResult<List<Artist>>> =
        transformModel {
            webService.getTopArtists(number)
        }

    suspend fun transformModel(result: suspend () -> Flow<ApiResult<ArtistsResponse>>) =
        flow {
            result().map {
                when (it) {
                    is ApiResult.OnSuccess -> ApiResult.OnSuccess(it.data.toTopArtist())
                    is ApiResult.OnFailure -> ApiResult.OnFailure(it.throwable)
                }
            }.collect {
                emit(it)
            }
            return@flow
        }.flowOn(Dispatchers.Default)


}