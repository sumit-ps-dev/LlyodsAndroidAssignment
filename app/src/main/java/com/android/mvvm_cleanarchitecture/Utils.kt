package com.android.mvvm_cleanarchitecture

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import retrofit2.Response
import java.io.IOException

/**
 * Naming convention for Network call lambda
 */
typealias NetworkAPIInvoke<T> = suspend () -> Response<T>


/**
 * Type-restricts the result of network calls to success and failure. The type
 * <T> represents the model class expected from the API call in case of a success
 * In case of success, the result will be wrapped around the OnSuccess class
 * In case of error, the throwable causing the error will be wrapped around OnFailure class
 */
sealed class ApiResult<out T : Any> {
    data class OnSuccess<out T : Any>(val data: T) : ApiResult<T>()
    data class OnFailure(val throwable: Throwable) : ApiResult<Nothing>()
}


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

sealed class ViewState<out T : Any> {

    /**
     * State of UI where a loader should be showing to represents a loading UX to the user
     * @param isLoading will be true when the loading UX needs to display, false when not
     */
    data class Loading(val isLoading: Boolean) : ViewState<Nothing>()

    /**
     * State of UI where the operation requested has been completed successfully and the result
     * of type [T] has been provided to UI
     * @param output result object of [T] type representing the successful operation
     */
    data class Success<out T : Any>(val output: T) : ViewState<T>()

    /**
     * Represents the UI state where the operation requested by the UI has failed to complete
     * either due to a IO issue or a service exception and the same is conveyed back to the UI
     * to be shown the user
     * @param throwable [Throwable] instance containing the root cause of the failure in a [String]
     */
    data class Failure(val throwable: Throwable) : ViewState<Nothing>()

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


