package com.huda.movie.presentation.utils

sealed class TaskMultiResult<out SuccessData, out FailureData> {
    data class Success<out SuccessData, out FailureData>(val data: SuccessData) : TaskMultiResult<SuccessData, FailureData>()
    data class Failure<out SuccessData, out FailureData>(val error: Error, val data: FailureData) : TaskMultiResult<SuccessData, FailureData>()

    inline fun <V> fold(
        success: (data: SuccessData) -> V,
        failure: (error: Error, data: FailureData) -> V): V =
        when (this) {
            is Success -> success(this.data)
            is Failure -> failure(this.error, this.data)
        }
}

sealed class TaskResult<out SuccessData> {
    data class Success<out SuccessData>(val data: SuccessData) : TaskResult<SuccessData>()
    data class Failure<out SuccessData>(val error: Error) : TaskResult<SuccessData>()

    inline fun <V> fold(
        success: (data: SuccessData) -> V,
        failure: (error: Error) -> V): V =
        when (this) {
            is Success -> success(this.data)
            is Failure -> failure(this.error)
        }
}