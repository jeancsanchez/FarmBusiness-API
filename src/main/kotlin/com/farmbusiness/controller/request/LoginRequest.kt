package com.farmbusiness.controller.request

data class LoginRequest(
    val emailOrCpfOrCnpj: String,
    val password: String
)