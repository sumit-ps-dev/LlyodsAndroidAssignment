package com.android.mvvm_cleanarchitecture.contract

import com.android.mvvm_cleanarchitecture.ApiResult
import kotlinx.coroutines.flow.Flow

interface IUseCase<in I : Any, out O : Any>  {

    suspend fun execute(input: I): Flow<ApiResult<O>>
}