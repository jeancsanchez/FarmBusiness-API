package com.farmbusiness.controller.request.product

import com.farmbusiness.controller.model.product.ProductModel
import com.farmbusiness.extension.toDate
import javax.validation.constraints.NotBlank

/**
 * @author @jeancsanchez
 * @created 24/12/2023
 * Jesus loves you.
 */

data class ProductRequest(
    val id: Int? = null,
    @NotBlank(message = "title is required") val title: String,
    val description: String,
    val presentation: String,
    val categoryId: Int?,
    @NotBlank(message = "Subcategory is required") val subCategoryId: Int,
    val images: List<String>?, // base64
    val code: String?,
    val shelfLifeMillis: Long,
    val batch: String,
    val unitPrice: Double,
    val totalItems: Int
) {
    fun toModel(): ProductModel =
        ProductModel(
            id = id,
            title = title,
            description = description,
            presentation = presentation,
            code = code,
            shelfLife = shelfLifeMillis.toDate(),
            batch = batch,
            unitPrice = unitPrice,
            totalItems = totalItems
        )
}