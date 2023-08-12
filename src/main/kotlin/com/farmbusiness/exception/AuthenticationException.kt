package com.farmbusiness.exception

class AuthenticationException(
    override val message: String, val errorCode: String
): Exception()