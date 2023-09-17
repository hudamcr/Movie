package com.huda.movie.presentation.utils

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

inline fun <Response> tryCatchTaskResult(
    tryAction: () -> TaskResult<Response>,
    catchAction: (error: Error) -> TaskResult<Response>
): TaskResult<Response> {
    return try {
        tryAction()
    } catch (e: UnknownHostException) {
        catchAction(NoInternet(e.message.orEmpty()))
    } catch (e: ConnectException) {
        catchAction(NoInternet(e.message.orEmpty()))
    } catch (e: SocketTimeoutException) {
        catchAction(NoInternet(e.message.orEmpty()))
    } catch (e: Exception) {
        catchAction(GeneralError(e, e.message.orEmpty()))
    }
}

inline fun <Response, ErrorResponse> tryCatchTaskMultiResult(
    tryAction: () -> TaskMultiResult<Response, ErrorResponse>,
    catchAction: (error: Error) -> TaskMultiResult<Response, ErrorResponse>
): TaskMultiResult<Response, ErrorResponse> {
    return try {
        tryAction()
    } catch (e: UnknownHostException) {
        catchAction(NoInternet(e.message.orEmpty()))
    } catch (e: ConnectException) {
        catchAction(NoInternet(e.message.orEmpty()))
    } catch (e: SocketTimeoutException) {
        catchAction(NoInternet(e.message.orEmpty()))
    } catch (e: Exception) {
        catchAction(GeneralError(e, e.message.orEmpty()))
    }
}