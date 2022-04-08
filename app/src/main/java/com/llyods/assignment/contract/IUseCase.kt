package com.llyods.assignment.contract


import com.llyods.assignment.data.ApiResult
import kotlinx.coroutines.flow.Flow

interface IUseCase<in I : Any, out O : Any>  {

    val repository: IRepository

    suspend fun execute(input: I): Flow<ApiResult<O>>
}