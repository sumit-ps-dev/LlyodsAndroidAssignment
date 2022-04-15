package com.llyods.assignment.domain.usecase

import com.llyods.assignment.data.response.ApiResult
import com.llyods.assignment.domain.model.Artist
import com.llyods.assignment.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopArtistUseCase @Inject constructor(
    override val repository: IRepository,
) : IUseCase<Int, List<Artist>> {

    override suspend fun execute(input: Int): Flow<ApiResult<List<Artist>>> =
        repository.getArtists(input)

}