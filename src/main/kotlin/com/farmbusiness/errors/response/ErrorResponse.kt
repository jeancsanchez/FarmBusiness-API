package com.farmbusiness.controller.response

data class ErrorResponse(
    val httpCode: Int,
    val message: String,
    val internalCode: String? = null,
    val errors: List<FieldErrorResponse>? = emptyList()
)