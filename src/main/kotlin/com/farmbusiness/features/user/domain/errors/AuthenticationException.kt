package com.farmbusiness.domain.errors.exceptions

import com.farmbusiness.domain.errors.Errors

class AuthenticationException(
    override val message: String = Errors.ML000.message,
    val errorCode: String = Errors.ML000.code
) : Exception()