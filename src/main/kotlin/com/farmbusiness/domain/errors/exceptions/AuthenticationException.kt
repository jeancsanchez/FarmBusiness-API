package com.farmbusiness.domain.errors.exceptions

class AuthenticationException(
    override val message: String,
    val errorCode: String
) : Exception()