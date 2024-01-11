package com.farmbusiness.controller.error

import com.farmbusiness.controller.response.ErrorResponse
import com.farmbusiness.domain.errors.Errors
import com.farmbusiness.domain.errors.exceptions.AuthenticationException
import com.farmbusiness.domain.errors.exceptions.NotFoundException
import com.farmbusiness.utils.extension.buildErrorResponse
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.validation.UnexpectedTypeException

/**
 * @author @jeancsanchez
 * @created 11/01/2024
 * Jesus loves you.
 */

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ErrorResponse(
                    httpCode = HttpStatus.INTERNAL_SERVER_ERROR.value(),

                    message = Errors.ML100.message,
                    internalCode = Errors.ML100.code
                )
            )
    }

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

    @ExceptionHandler(MissingKotlinParameterException::class)
    fun handleKotlinParameterException(error: MissingKotlinParameterException): ResponseEntity<ErrorResponse> {
        val message = "Check parameter: ${error.parameter.name}"
        error.printStackTrace()

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                ErrorResponse(
                    httpCode = HttpStatus.BAD_REQUEST.value(),
                    message = Errors.ML002.message.format(message),
                    internalCode = Errors.ML002.code
                )
            )
    }

    @ExceptionHandler(UnexpectedTypeException::class)
    fun handleUnexpectedTypeException(error: UnexpectedTypeException): ResponseEntity<ErrorResponse> {
        val message = error.message
            ?.split("validating type.*\\.".toRegex())
            ?.last()
            ?.trim()
            ?: ""

        error.printStackTrace()

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                ErrorResponse(
                    httpCode = HttpStatus.BAD_REQUEST.value(),
                    message = Errors.ML002.message.format(message),
                    internalCode = Errors.ML002.code
                )
            )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(error: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val message = error.message
            .substringAfterLast("[")
            .replace("]", "")


        error.printStackTrace()

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                ErrorResponse(
                    httpCode = HttpStatus.BAD_REQUEST.value(),
                    message = Errors.ML002.message.format(message),
                    internalCode = Errors.ML002.code
                )
            )
    }
}