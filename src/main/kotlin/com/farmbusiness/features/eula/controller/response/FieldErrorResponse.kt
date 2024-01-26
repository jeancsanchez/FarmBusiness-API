package com.farmbusiness.features.eula.controller.response

data class FieldErrorResponse(
        val message: String,
        val field: String? = ""
)
