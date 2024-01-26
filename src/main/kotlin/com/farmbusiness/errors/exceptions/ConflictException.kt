package com.farmbusiness.errors.exceptions

class ConflictException(
    override val message: String,
    val errorCode: String
) : Exception()