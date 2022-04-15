package com.llyods.assignment.domain.usecase


import com.llyods.assignment.domain.repository.IRepository
import com.llyods.assignment.data.response.ApiResult
import kotlinx.coroutines.flow.Flow

interface IUseCase<in I : Any, out O : Any>  {

    val repository: IRepository

    suspend fun execute(input: I): Flow<ApiResult<O>>
}