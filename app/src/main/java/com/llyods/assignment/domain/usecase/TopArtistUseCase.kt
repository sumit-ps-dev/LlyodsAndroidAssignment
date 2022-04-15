package com.llyods.assignment.domain.usecase

import com.llyods.assignment.data.mapper.ArtistMapper.toTopArtist
import com.llyods.assignment.data.response.ApiResult
import com.llyods.assignment.data.response.ApiResult.OnFailure
import com.llyods.assignment.data.response.ApiResult.OnSuccess
import com.llyods.assignment.data.response.ArtistsResponse
import com.llyods.assignment.domain.model.TopArtist
import com.llyods.assignment.domain.repository.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class TopArtistUseCase @Inject constructor(
    override val repository: IRepository,
) : IUseCase<Int, List<TopArtist>> {

    override suspend fun execute(input: Int): Flow<ApiResult<List<TopArtist>>> =
        transformModel {
            repository.getArtists(input)
        }

    suspend fun transformModel(result: suspend () -> Flow<ApiResult<ArtistsResponse>>) =
        flow {
            result().map { response ->
                when (response) {
                    is OnSuccess -> OnSuccess(response.data.toTopArtist())
                    is OnFailure -> OnFailure(response.throwable)
                }
            }.collect {
                emit(it)
            }
            return@flow
        }.flowOn(Dispatchers.Default)


}