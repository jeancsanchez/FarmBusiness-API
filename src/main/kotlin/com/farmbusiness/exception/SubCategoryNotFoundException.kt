package com.farmbusiness.exception

class SubCategoryNotFoundException(
    override val message: String = "Subcategory not found",
    val errorCode: Int = 404
) : Exception(message)