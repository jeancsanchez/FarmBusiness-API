package com.farmbusiness.domain.errors

enum class Errors(val code: String, val message: String) {
    ML100("ML-100", "Internal Server Error"),
    ML000("ML-000", "Access Denied"),
    ML001("ML-001", "Invalid Credentials"),
    ML002("ML-002", "Invalid Request."),

    ML101("ML-101", "Book [%s] not exists"),
    ML102("ML-102", "Cannot update book with status [%s]"),
    ML201("ML-201", "Customer [%s] not exists"),
    ML202("ML-202", "Product [%s] not exists"),
    ML203("ML-203", "Category [%s] not exists"),
    ML204("ML-204", "Subcategory [%s] not exists"),
    ML205("ML-205", "User [%s] already exists"),
}