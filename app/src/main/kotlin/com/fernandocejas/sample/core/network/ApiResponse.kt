package com.fernandocejas.sample.core.network

import com.fernandocejas.sample.core.functional.Either
import com.fernandocejas.sample.core.functional.toLeft
import com.fernandocejas.sample.core.functional.toRight

sealed class ApiResponse<out T, out E> {
    /**
     * Represents successful network responses (2xx).
     */
    data class Success<T>(val body: T) : ApiResponse<T, Nothing>()

    sealed class Error<E> : ApiResponse<Nothing, E>() {
        /**
         * Represents server (50x) and client (40x) errors.
         */
        data class HttpError<E>(val code: Int, val errorBody: E?) : Error<E>()

        /**
         * Represent IOExceptions and connectivity issues.
         */
        data object NetworkError : Error<Nothing>()

        /**
         * Represent SerializationExceptions.
         */
        data object SerializationError : Error<Nothing>()
    }
}

// Side Effect helpers
inline fun <T, E> ApiResponse<T, E>.onSuccess(block: (T) -> Unit): ApiResponse<T, E> {
    if (this is ApiResponse.Success) {
        block(body)
    }
    return this
}

fun <T, E> ApiResponse<T, E>.toEither(): Either<E?, T> {
    return when (this) {
        is ApiResponse.Success -> body.toRight()
        is ApiResponse.Error.HttpError -> errorBody.toLeft()
        is ApiResponse.Error.NetworkError -> null.toLeft()
        is ApiResponse.Error.SerializationError -> null.toLeft()
    }
}

fun <T, E, F, D> ApiResponse<T, E>.toEither(
    successTransform: (T) -> D,
    errorTransform: (ApiResponse.Error<E>) -> F,
): Either<F, D> {
    return when (this) {
        is ApiResponse.Success -> successTransform(body).toRight()
        is ApiResponse.Error -> errorTransform(this).toLeft()
    }
}
