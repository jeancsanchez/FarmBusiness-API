package com.farmbusiness.utils.extension

import com.farmbusiness.controller.response.ErrorResponse
import com.farmbusiness.domain.errors.Errors
import com.farmbusiness.domain.errors.exceptions.AuthenticationException
import org.springframework.http.HttpStatus

/**
 * @author @jeancsanchez
 * @created 11/01/2024
 * Jesus loves you.
 */

fun AuthenticationException.buildErrorResponse(): ErrorResponse {
    val status = when (errorCode) {
        Errors.ML000.code -> HttpStatus.FORBIDDEN
        else -> HttpStatus.UNAUTHORIZED
    }

    return ErrorResponse(
        httpCode = status.value(),
        message = message,
        internalCode = errorCode
    )
}