package com.farmbusiness.domain.errors.exceptions

class ConflictException(
    override val message: String,
    val errorCode: String
) : Exception()