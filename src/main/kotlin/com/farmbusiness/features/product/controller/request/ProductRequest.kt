package com.farmbusiness.features.product.controller.request

import javax.validation.constraints.NotNull

/**
 * @author @jeancsanchez
 * @created 24/12/2023
 * Jesus loves you.
 */

data class ProductRequest(
        val id: Int? = null,
        @NotNull(message = "title is required") val title: String,
        val description: String,
        val presentation: String,
        @NotNull(message = "Category is required") val categoryId: Int,
        @NotNull(message = "Subcategory is required") val subCategoryId: Int,
        val images: List<String>?, // base64
        val code: String?,
        val shelfLifeMillis: Long,
        val batch: String,
        val unitPrice: Double,
        val totalItems: Int
)