package com.huda.movie.presentation.utils

sealed class Error (open val message: String)

fun Error.toCode(): Int {
    return when (this) {
        is Unauthorized -> 401
        is Forbidden -> 403
        is NotFound -> 404
        is ServerError -> 500
        is BadGateway -> 502
        is ServiceUnavailable -> 503
        else -> 500
    }
}

/*
* General or Universal or Error that you don't know what it is
* but you still need to handle the UI for it.
* */
data class UnknownException(override val message: String = "unknown exception"): Exception(message)
data class GeneralError(val exception: Exception, override val message: String = ""): Error(message)


/* General Network Error */
data class NoInternet(override val message: String = ""): Error(message)
data class ServerError(override val message: String = ""): Error(message)
data class BadGateway(override val message: String = ""): Error(message)
data class ServiceUnavailable(override val message: String = ""): Error(message)
data class Unauthorized(override val message: String = ""): Error(message)
data class Forbidden(override val message: String = ""): Error(message)
data class NotFound(override val message: String = ""): Error(message)



/* Specific Error */
data class TokenExpired(override val message: String = ""): Error(message)
data class TokenInvalid(override val message: String = ""): Error(message)