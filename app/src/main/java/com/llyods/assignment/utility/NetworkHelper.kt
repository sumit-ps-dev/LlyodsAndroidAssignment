package com.llyods.assignment

import com.llyods.assignment.data.ApiResult
import com.llyods.assignment.viewmodel.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import retrofit2.Response
import java.io.IOException

/**
 * Naming convention for Network call lambda
 */
typealias NetworkAPIInvoke<T> = suspend () -> Response<T>


suspend fun <T : Any> performNetworkApiCall(
    errorMessage: String = "Network error!",
    allowRetries: Boolean = true,
    numberOfRetries: Int = 2,
    networkApiCall: NetworkAPIInvoke<T>,
): kotlinx.coroutines.flow.Flow<ApiResult<T>> {
    var delayDuration = 1000L
    val delayFactor = 2
    return flow {
        val response = networkApiCall()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(ApiResult.OnSuccess(it))
            }
                ?: emit(ApiResult.OnFailure(IOException("API call successful but empty response body")))
            return@flow
        }
        emit(
            ApiResult.OnFailure(
                IOException(
                    "API call failed with error - ${
                        response.errorBody()
                            ?.string() ?: errorMessage
                    }"
                )
            )
        )
        return@flow
    }.catch { e ->
        emit(ApiResult.OnFailure(IOException("Exception during network API call: ${e.message}")))
        return@catch
    }.retryWhen { cause, attempt ->
        if (!allowRetries || attempt > numberOfRetries || cause !is IOException) return@retryWhen false
        delay(delayDuration)
        delayDuration *= delayFactor
        return@retryWhen true
    }.flowOn(Dispatchers.IO)
}


suspend fun <T : Any> getViewStateFlowForNetworkCall(ioOperation: suspend () -> Flow<ApiResult<T>>) =
    flow {
        emit(ViewState.Loading(true))
        ioOperation().map {
            when (it) {
                is ApiResult.OnSuccess -> ViewState.Success(it.data)
                is ApiResult.OnFailure -> ViewState.Failure(it.throwable)
            }
        }.collect {
            emit(it)
        }
        emit(ViewState.Loading(false))
    }.flowOn(Dispatchers.IO)


