package com.farmbusiness.exception

class ProductNotFoundException(
    override val message: String = "Product not found",
    val errorCode: Int = 404
): Exception()