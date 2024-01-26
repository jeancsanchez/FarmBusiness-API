package com.farmbusiness.errors.handler

import com.farmbusiness.errors.Errors
import com.farmbusiness.errors.exceptions.ConflictException
import com.farmbusiness.errors.exceptions.NotFoundException
import com.farmbusiness.errors.response.ErrorResponse
import com.farmbusiness.features.eula.controller.response.FieldErrorResponse
import com.farmbusiness.features.user.domain.errors.AuthenticationException
import com.farmbusiness.utils.extension.buildErrorResponse
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
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
        ex.printStackTrace()

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

    @ExceptionHandler(MissingKotlinParameterException::class, HttpMessageNotReadableException::class)
    fun handleKotlinParameterException(
            error1: MissingKotlinParameterException?,
            error2: HttpMessageNotReadableException?
    ): ResponseEntity<ErrorResponse> {
        val error = error1 ?: error2
        val message = "Check parameter: ${error1?.parameter?.name ?: error2?.message}"
        error?.printStackTrace()

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse(
                                httpCode = HttpStatus.BAD_REQUEST.value(),
                                message = Errors.ML002.message,
                                internalCode = Errors.ML002.code,
                                errors = listOf(
                                        FieldErrorResponse(message = message)
                                )
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
                                message = Errors.ML002.message,
                                internalCode = Errors.ML002.code,
                                errors = listOf(
                                        FieldErrorResponse(message = message)
                                )
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
                                message = Errors.ML002.message,
                                internalCode = Errors.ML002.code,
                                errors = listOf(
                                        FieldErrorResponse(message = message)
                                )
                        )
                )
    }

    @ExceptionHandler(ConflictException::class)
    fun handleConflictException(error: ConflictException): ResponseEntity<ErrorResponse> {
        error.printStackTrace()

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        ErrorResponse(
                                httpCode = HttpStatus.CONFLICT.value(),
                                message = error.message,
                                internalCode = error.errorCode
                        )
                )
    }
}