package com.farmbusiness.features.product.controller.response

data class ProductResponse(
        val id: Int,
        val title: String,
        val description: String,
        val presentation: String,
        val images: List<String>,
        val code: String?,
        val shelfLifeMillis: Long,
        val batch: String,
        val unitPrice: Double,
        val totalItems: Int
)