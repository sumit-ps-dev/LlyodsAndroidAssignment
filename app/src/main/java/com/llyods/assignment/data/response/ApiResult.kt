package com.llyods.assignment.data.response

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