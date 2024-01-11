package com.farmbusiness.controller.error

import com.farmbusiness.controller.response.ErrorResponse
import com.farmbusiness.domain.errors.exceptions.AuthenticationException
import com.farmbusiness.domain.errors.exceptions.NotFoundException
import com.farmbusiness.utils.extension.buildErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * @author @jeancsanchez
 * @created 11/01/2024
 * Jesus loves you.
 */

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException::class)
    fun handleUnauthorizedException(error: AuthenticationException): ResponseEntity<ErrorResponse> {
        error.printStackTrace()
        return error.buildErrorResponse()
            .run {
                ResponseEntity
                    .status(httpCode)
                    .body(this)
            }
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(error: NotFoundException): ResponseEntity<ErrorResponse> {
        error.printStackTrace()

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                ErrorResponse(
                    httpCode = HttpStatus.NOT_FOUND.value(),
                    message = error.message,
                    internalCode = error.errorCode
                )
            )
    }
}