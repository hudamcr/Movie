package com.huda.movie.presentation.utils

internal object ApiResponseCodeMapper {
    operator fun invoke(httpStatusCode: Int, responseCode: Int?, message: String = ""): Error {
        return when(responseCode) {
            401, 1016, 1017, 4001, 4012, 10002 -> TokenExpired(message)
            12, 1012, 1013, 1014, 1015, 4010, 4011, 4013, 4014, 4030 -> TokenInvalid(message)
            else -> mapHttpStatusCode(httpStatusCode, responseCode, message)
        }
    }

    private fun mapHttpStatusCode(httpStatusCode: Int, responseCode: Int?, message: String): Error {
        return when  {
            httpStatusCode >= 500 -> ServerError("Server Error")
            httpStatusCode == 401 -> Unauthorized(message)
            httpStatusCode == 403 -> Forbidden(message)
            httpStatusCode == 404 -> NotFound(message)
            else -> {
                val errorMessage = "$message. [code:${responseCode.orZero()}]"
                GeneralError(UnknownException(errorMessage), errorMessage)
            }
        }
    }
}

