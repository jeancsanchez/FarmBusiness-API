package com.farmbusiness.controller.request.product

import com.farmbusiness.controller.model.ProductImageModel
import com.farmbusiness.controller.model.ProductModel
import com.farmbusiness.extension.toDate
import java.util.*

/**
 * @author @jeancsanchez
 * @created 24/12/2023
 * Jesus loves you.
 */

data class ProductRequest(
    val id: Int? = null,
    val title: String,
    val description: String,
    val presentation: String,
    val images: List<ProductImageModel>?,
    val code: String?,
    val shelfLifeMillis: Long,
    val batch: String,
    val unitPrice: Double,
    val totalItems: Int

    // TODO
//    val freight: String,

    // TODO
//    val dicount: String,
) {
    fun toModel(): ProductModel =
        ProductModel(
            id = id,
            title = title,
            description = description,
            presentation = presentation,
            images = images,
            code = code,
            createdAt = Date(), // now
            shelfLife = shelfLifeMillis.toDate(),
            batch = batch,
            unitPrice = unitPrice,
            totalItems = totalItems
        )
}