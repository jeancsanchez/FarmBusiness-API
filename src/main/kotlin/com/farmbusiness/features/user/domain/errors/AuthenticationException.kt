package com.farmbusiness.features.user.domain.errors

import com.farmbusiness.errors.Errors

class AuthenticationException(
        override val message: String = Errors.ML000.message,
        val errorCode: String = Errors.ML000.code
) : Exception()