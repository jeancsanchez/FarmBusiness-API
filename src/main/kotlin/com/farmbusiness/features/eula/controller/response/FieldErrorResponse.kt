package com.farmbusiness.controller.response

data class FieldErrorResponse(
    val message: String,
    val field: String? = ""
)
