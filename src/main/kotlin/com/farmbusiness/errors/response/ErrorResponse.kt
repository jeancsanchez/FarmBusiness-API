package com.farmbusiness.errors.response

import com.farmbusiness.features.eula.controller.response.FieldErrorResponse

data class ErrorResponse(
    val httpCode: Int,
    val message: String,
    val internalCode: String? = null,
    val errors: List<FieldErrorResponse>? = emptyList()
)