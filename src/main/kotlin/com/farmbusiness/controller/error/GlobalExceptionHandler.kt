package com.farmbusiness.controller.error

import com.farmbusiness.controller.response.ErrorResponse
import com.farmbusiness.domain.errors.exceptions.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * @author @jeancsanchez
 * @created 11/01/2024
 * Jesus loves you.
 */

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(ex: Exception): ResponseEntity<ErrorResponse> {
        val error = ex as NotFoundException
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