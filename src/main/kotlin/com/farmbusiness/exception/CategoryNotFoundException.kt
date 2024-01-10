package com.farmbusiness.exception

class CategoryNotFoundException(
    override val message: String = "Category not found",
    val errorCode: Int = 404
) : Exception(message)